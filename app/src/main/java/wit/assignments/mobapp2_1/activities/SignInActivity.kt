package wit.assignments.mobapp2_1.activities

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import timber.log.Timber
import wit.assignments.mobapp2_1.R
import wit.assignments.mobapp2_1.databinding.ActivitySigninBinding
import wit.assignments.mobapp2_1.helpers.createLoader
import wit.assignments.mobapp2_1.helpers.hideLoader
import wit.assignments.mobapp2_1.helpers.showLoader


class SignInActivity : AppCompatActivity(), View.OnClickListener{


    private lateinit var auth: FirebaseAuth // Declare Auth
    lateinit var loader : AlertDialog
    private lateinit var signinBinding: ActivitySigninBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signinBinding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(signinBinding.root)

        signinBinding.emailSignInButton.setOnClickListener(this)
        signinBinding.emailCreateAccountButton.setOnClickListener(this)
        signinBinding.signOutButton.setOnClickListener(this)
        signinBinding.verifyEmailButton.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()// Initialize Firebase Auth
        loader = createLoader(this)
    }

    public override fun onStart() {//on_start_check_user
        super.onStart()
        val currentUser = auth.currentUser// Check if user is signed in (non-null) and update UI accordingly.
        updateUI(currentUser)
    }

    private fun createAccount(email: String, password: String) {
        Timber.d( "createAccount:$email")
        if (!validateForm()) {
            return
        }
        showLoader(loader, "Creating Account...")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {// Sign in success, update UI with the signed-in user's information
                    Timber.d( "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else { // If sign in fails, display a message to the user.
                    Timber.w( "createUserWithEmail:failure $task.exception")
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                hideLoader(loader)//EXCLUDE
            }
    }

    private fun signIn(email: String, password: String) {
        Timber.d( "signIn:$email")
        if (!validateForm()) {
            return
        }
        showLoader(loader, "Logging In...")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {// Sign in success, update UI with the signed-in user's information
                    Timber.d( "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {// If sign in fails, display a message to the user.
                    Timber.w( "signInWithEmail:failure $task.exception")
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                if (!task.isSuccessful) {//EXCLUDE
                    signinBinding.status.setText(R.string.auth_failed)
                }
                hideLoader(loader)
            }
    }

    private fun signOut() {
        auth.signOut()
        updateUI(null)
    }

    private fun sendEmailVerification() {
        signinBinding.verifyEmailButton.isEnabled = false// Disable button
        val user = auth.currentUser
        user?.sendEmailVerification()// Send verification email
            ?.addOnCompleteListener(this) { task ->// EXCLUDE
                signinBinding.verifyEmailButton.isEnabled = true// Re-enable button
                if (task.isSuccessful) {
                    Toast.makeText(baseContext,
                        "Verification email sent to ${user.email} ",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Timber.e( "sendEmailVerification $task.exception")
                    Toast.makeText(baseContext,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateForm(): Boolean {
        val email = signinBinding.fieldEmail.text.toString()
        val password = signinBinding.fieldPassword.text.toString()

        if (TextUtils.isEmpty(email)) {
            signinBinding.fieldEmail.error = "Required."
            return false
        } else {
            signinBinding.fieldEmail.error = null
        }

        if (TextUtils.isEmpty(password)) {
            signinBinding.fieldPassword.error = "Required."
            return false
        } else {
            signinBinding.fieldPassword.error = null
        }
        return true
    }

    private fun updateUI(user: FirebaseUser?) {
        hideLoader(loader)
        if (user != null) {
            signinBinding.status.text = getString(R.string.emailpassword_status_fmt, user.email, user.isEmailVerified)
            signinBinding.detail.text = getString(R.string.firebase_status_fmt, user.uid)
            signinBinding.emailPasswordButtons.visibility = View.GONE
            signinBinding.emailPasswordFields.visibility = View.GONE
            signinBinding.signedInButtons.visibility = View.VISIBLE
            signinBinding.verifyEmailButton.isEnabled = !user.isEmailVerified
        } else {
            signinBinding.status.setText(R.string.signed_out)
            signinBinding.detail.text = null
            signinBinding.emailPasswordButtons.visibility = View.VISIBLE
            signinBinding.emailPasswordFields.visibility = View.VISIBLE
            signinBinding.signedInButtons.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.emailCreateAccountButton -> createAccount(signinBinding.fieldEmail.text.toString(), signinBinding.fieldPassword.text.toString())
            R.id.emailSignInButton -> signIn(signinBinding.fieldEmail.text.toString(), signinBinding.fieldPassword.text.toString())
            R.id.signOutButton -> signOut()
            R.id.verifyEmailButton -> sendEmailVerification()
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}