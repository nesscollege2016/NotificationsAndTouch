package tomerbu.edu.notificationsandtouch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {



    // UI references.
    private AutoCompleteTextView etEmail;
    private EditText etPassword;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         etEmail = (AutoCompleteTextView) findViewById(R.id.email);


        etPassword = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btnSignIn);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithFirebase();
            }
        });

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                registerWithFirebase();
            }
        });

    }


    private void registerWithFirebase() {
        if (testErrors())
            return;

        FirebaseAuth.getInstance().
                createUserWithEmailAndPassword(getEmail(), getPassword())
                .addOnSuccessListener(mSuccessListener)
                .addOnFailureListener(mOnFailureListener);
    }

    private void loginWithFirebase() {
        if (testErrors())
            return;


        FirebaseAuth.getInstance().
                signInWithEmailAndPassword(getEmail(), getPassword())
                .addOnSuccessListener(mSuccessListener)
                .addOnFailureListener(mOnFailureListener);
    }

    private boolean testErrors() {
        String email = getEmail();

        if (!isEmailValid(email)){
            etEmail.setError("Email must contain @ ");
            return true;
        }

        String password = getPassword();
        if (!isPasswordValid(password)){
            etPassword.setError("Password to short");
            return true;
        }
        return false;
    }


    OnSuccessListener<AuthResult> mSuccessListener = new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    };


    OnFailureListener mOnFailureListener =  new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Snackbar.make(btnLogin, e.getMessage(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new OnClickListener() {
                        public void onClick(View view) {}}).show();
        }
    };


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 6;
    }


    public String getEmail() {
        return etEmail.getText().toString();
    }

    public String getPassword() {
        return etPassword.getText().toString();
    }
}

