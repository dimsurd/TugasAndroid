package com.example.eventapp.pages;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.eventapp.R;

public class SignInFragment extends Fragment {


    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        ImageView btnBack= view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        final EditText email = view.findViewById(R.id.etEmail);
        final EditText password = view.findViewById(R.id.etPassword);

        Button btnSignIn = view.findViewById(R.id.btnLogin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( isValidate(email, password) ){
                    gotoHome();
                }
            }
        });

        return view;
    }

    private void gotoHome(){
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new HomeFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showAlert(String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(requireContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder1.create();
        alert.show();
    }

    private boolean isValidate(EditText email, EditText password){
        boolean isValid = false;

        if (email.getText().toString().equals("")){
            showAlert("Email Required.");
        } else if (password.getText().toString().equals("")){
            showAlert("Password Required.");
        } else if (email.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")
        ){
            return true;
        } else {
            showAlert("Wrong Email / Password .");
        }

        return isValid;
    }


}