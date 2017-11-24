package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Option2Activity extends AppCompatActivity implements TextWatcher, View.OnClickListener{

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option2);
        bindView();
        initView();
    }

    private void bindView(){
        etName = (EditText) findViewById(R.id.et_name2);
        etPwd = (EditText) findViewById(R.id.et_pwd2);
        etEmail = (EditText) findViewById(R.id.et_email2);
        etPhone = (EditText) findViewById(R.id.et_phone2);
    }

    private void initView(){
        // OnClickListener
        findViewById(R.id.btn_validate2).setOnClickListener(this);

        // TextChangedListener
        etName.addTextChangedListener(this);
        etPwd.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
    }

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
        validateEditText();

        if(validateEditText()){
            validateName();
            validatePwd();
            validateEmail();
            validatePhone();
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_validate2) {
            if(validateEditText()&&validateName()&&validatePwd()&&validateEmail()&&validatePhone()) {
                Toast.makeText(this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Option2Activity.this, "Please, try again.", Toast.LENGTH_SHORT).show();
            }
        }
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
