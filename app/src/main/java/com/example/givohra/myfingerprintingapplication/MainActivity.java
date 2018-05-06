package com.example.givohra.myfingerprintingapplication;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;
import com.multidots.fingerprintauth.AuthErrorCodes;

public class MainActivity extends AppCompatActivity implements FingerPrintAuthCallback {

    FingerPrintAuthHelper mFingerPrintAuthHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFingerPrintAuthHelper.stopAuth();
    }

    @Override
    public void onNoFingerPrintHardwareFound() {
        Toast.makeText(this, "No FingerPrint Sensor detected!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNoFingerPrintRegistered() {
        Toast.makeText(this, "Need to register a fingerprint!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBelowMarshmallow() {
        Toast.makeText(this, "Your device does not support fingerprinting!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(this,DataActivity.class);
        startActivity(intent);

    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        Toast.makeText(this, "fail!" + errorMessage, Toast.LENGTH_SHORT).show();
        switch (errorCode) {    //Parse the error code for recoverable/non recoverable error.
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                //Cannot recognize the fingerprint scanned.
                Toast.makeText(this, "Cannot Recognize Fingerprint!", Toast.LENGTH_SHORT).show();
                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
                //This is not recoverable error. Try other options for user authentication. like pin, password.
                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
                //Any recoverable error. Display message to the user.
                break;
        }

    }
}
