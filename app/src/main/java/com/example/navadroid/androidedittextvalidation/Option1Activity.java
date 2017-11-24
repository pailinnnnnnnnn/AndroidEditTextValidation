package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Option1Activity extends AppCompatActivity {

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);
        bindView();
        initView();
    }

    private void bindView() {
        etName = (EditText) findViewById(R.id.et_name1);
        etPwd = (EditText) findViewById(R.id.et_pwd1);
        etEmail = (EditText) findViewById(R.id.et_email1);
        etPhone = (EditText) findViewById(R.id.et_phone1);
    }

    private void initView() {
        findViewById(R.id.btn_validate1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEditText()&&validateName()&&validatePwd()&&validateEmail()&&validatePhone()) {
                    Toast.makeText(Option1Activity.this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
                    // SnackBar?
                }else {
                    Toast.makeText(Option1Activity.this, "Please, try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This can be ignored
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This can be ignored
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEditText(); // OR validation can be specific (only for this EditText)
                validateName();
            }
        });

        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO: add your Password validation here


                validatePwd();

            }
        });

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePhone();

            }
        });
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEmail();
            }
        });

//        etPhone.addTextChangedListener(...);
//        etEmail.addTextChangedListener(...);
    }

    // To validate all EditTexts
    private boolean validateEditText() {
        boolean isValidated = true;
        if (etName.getText().toString().length() == 0) {
            etName.setError("Required");
            isValidated = false;
        } else if (etPwd.getText().toString().length() == 0) {
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
    private boolean validateName() {
        boolean nameIsValidated = true;
        String text = etName.getText().toString();
        if (!text.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$")) {
            nameIsValidated = false;
            etName.setError("Invalid format");
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

    private boolean validatePwd() {
        boolean pwdIsValidated = true;
        String text = etPwd.getText().toString();
        if (!text.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,13})")){
            pwdIsValidated = false;
            etPwd.setError("Require 6-13 char,[a-z],[A-Z],[0-9]");
        }

        return pwdIsValidated;
    }

    // To validate email
    private boolean validateEmail() {
        boolean emailIsValidated = true;
        String text = etEmail.getText().toString();
        if (!text.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            emailIsValidated = false;
            etEmail.setError("Invalid, hint: pailin@example.com");
        }

        return emailIsValidated;
    }

    // To validate phone number
    private boolean validatePhone() {

        boolean phoneIsValidated = true;
        String text = etPhone.getText().toString();
        if (!text.matches("^(?:0091|\\\\+91|0)[7-9][0-9]{7,8}$")) {
            phoneIsValidated = false;
            etPhone.setError("Invalid format");
        }
        return phoneIsValidated;
    }
}
