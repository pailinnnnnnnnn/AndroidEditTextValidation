package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Option3Activity extends AppCompatActivity {

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    private String textName, textPwd, textEmail, textPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option3);
        bindView();
        initView();
    }

    private void bindView() {
        etName = (EditText) findViewById(R.id.et_name3);
        etPwd = (EditText) findViewById(R.id.et_pwd3);
        etEmail = (EditText) findViewById(R.id.et_email3);
        etPhone = (EditText) findViewById(R.id.et_phone3);
    }

    private void initView() {
        // OnClickListener
        findViewById(R.id.btn_validate3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEditText()&&
                        validateName(etName,etName.getText().toString())&&
                        validatePwd(etPwd,etPwd.getText().toString())&&
                        validateEmail(etEmail,etEmail.getText().toString())&&
                        validatePhone(etPhone,etPhone.getText().toString())) {
                    Toast.makeText(Option3Activity.this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Option3Activity.this, "Please, try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // TextChangedListener
        etName.addTextChangedListener(new TextValidator(etName) {
            @Override
            public void validate(TextView textView, String text) {
                validateEditText();
                validateName(textView,text);
            }
        });

        etPwd.addTextChangedListener(new TextValidator(etPwd) {
            @Override
            public void validate(TextView textView, String text) {
                // TODO: add your Password validation here
                validatePwd(textView, text);
            }
        });

        etEmail.addTextChangedListener(new TextValidator(etEmail) {
            @Override
            public void validate(TextView textView, String text) {
                // TODO: add your Password validation here

                validateEmail(textView, text);
            }
        });

        etPhone.addTextChangedListener(new TextValidator(etPhone) {
            @Override
            public void validate(TextView textView, String text) {
                // TODO: add your Password validation here
                validatePhone(textView, text);
            }
        });
    }



    // To validate all EditTexts
    private boolean validateEditText(){
        boolean isValidated = true;
        if (etName.getText().toString().length() == 0) {
            etName.setError("Required");
            isValidated = false;
        }else if (etPwd.getText().toString().length() == 0) {
            etPwd.setError("Required");
            isValidated = false;
        } else if (etEmail.getText().toString().length() == 0) {
            etEmail.setError("Required");
            isValidated = false;
        } else if (etPhone.getText().toString().length() == 0) {
            etPhone.setError("Required");
            isValidated = false;
        }
        // TODO: add your EditText validation here

        return isValidated;
    }

    // To validate name
    private boolean validateName(TextView textView, String text) {
        boolean nameIsValidated = true;
//        String text = etName.getText().toString();
        if (!text.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
            nameIsValidated = false;
            textView.setError("Invalid format");
        }

        return nameIsValidated;
    }

    // To validate password

//(			# Start of group
//            (?=.*\d)		#   must contains one digit from 0-9
//            (?=.*[a-z])		#   must contains one lowercase characters
//            (?=.*[A-Z])		#   must contains one uppercase characters
//            (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
//            .		#     match anything with previous condition checking
//    {6,20}	#        length at least 6 characters and maximum of 20
//            )			# End of group
//

//    "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,13})"

    private boolean validatePwd(TextView textView, String text) {
        boolean pwdIsValidated = true;
        if (!text.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,13})")) {
            pwdIsValidated = false;
            textView.setError("Require 6-13 char,[a-z],[A-Z],[0-9]");
        }

        return pwdIsValidated;
    }

    // To validate email
    private boolean validateEmail(TextView textView, String text) {
        boolean emailIsValidated = true;
        if (!text.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            emailIsValidated = false;
            textView.setError("Invalid, hint: pailin@example.com");
        }

        return emailIsValidated;
    }

    // To validate phone number
    private boolean validatePhone(TextView textView, String text) {

        boolean phoneIsValidated = true;
        if (!text.matches("^(?:0091|\\\\+91|0)[7-9][0-9]{7,8}$")) {
            phoneIsValidated = false;
            textView.setError("Invalid format");
        }
        return phoneIsValidated;
    }
}
