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
import android.widget.ImageView;

import com.example.eventapp.R;


public class HomeFragment extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView btnLogout =view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert("Are You Sure Want To LogOut ?");
            }
        });

        return view;
    }

    private void gotoSignIn(){
        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new SignInFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void showAlert(String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(requireContext());
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gotoSignIn();
            }
        });

        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder1.create();
        alert.show();
    }

}