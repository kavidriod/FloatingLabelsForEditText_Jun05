package com.example.kavitha.floatinglabelsforedittext_jun05;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private EditText inputName, inputEmail, inputPassword;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputName = (EditText) findViewById(R.id.edit_text_name);
        inputEmail = (EditText) findViewById(R.id.edit_text_email);
        inputPassword = (EditText) findViewById(R.id.edit_text_password);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitForm();
            }
        });
    }


    /**
     * Validating form
     */
    private void submitForm() {

        if (!validateName()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private  class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.edit_text_name:
                    validateName();
                    break;
                case R.id.edit_text_email:
                    validateEmail();
                    break;
                case R.id.edit_text_password:
                    validatePassword();
                    break;
            }
        }
    }

        private boolean validateEmail(){
            if (inputEmail.getText().toString().trim().isEmpty()){
                inputLayoutEmail.setError(getString(R.string.err_msg_email));
                requestFocus(inputEmail);
                return false;
            }else{
                inputLayoutEmail.setErrorEnabled(false);
            }
            return true;
        }


    private boolean validatePassword(){
        if (inputPassword.getText().toString().trim().isEmpty()){
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        }else{
            inputLayoutPassword.setErrorEnabled(false);
        }
        return true;
    }


        private boolean validateName(){
            if (inputName.getText().toString().trim().isEmpty()){
inputLayoutName.setError(getString(R.string.err_msg_name));
                requestFocus(inputName);
                return false;
            }else{
                inputLayoutName.setErrorEnabled(false);
            }
            return true;
        }


    private void requestFocus(View editText){
        if (editText.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

    }
}
