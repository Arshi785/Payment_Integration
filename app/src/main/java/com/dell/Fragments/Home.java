package com.dell.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dell.paymentintegration.MainActivity;
import com.dell.paymentintegration.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.razorpay.RazorpayClient;

import org.json.JSONObject;

public class Home extends Fragment implements PaymentResultWithDataListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    public RazorpayClient razorpay;
    Button pay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        pay = view.findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                startPayment();
            }
        });


        return view;
    }

    public void startPayment() {

        // Reference to current activity

        final Activity activity=getActivity();
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {

            JSONObject options = new JSONObject();

            options.put("name", "The Spark Foundation");
            options.put("description", "Donation for Spark foundation");
            options.put("send_sms_hash",true);
            options.put("allow_rotation",true);
            options.put("key", "success@razorpay");
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "100");


            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);


            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }

    }
         @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
            try {
                Toast toast=new Toast(getActivity());
                View custom_view=getLayoutInflater().inflate(R.layout.snackbar,null);
                toast.setGravity(Gravity.BOTTOM|0,0,0);
                toast.setView(custom_view);
                toast.show();
            }catch (Exception e){
                Toast.makeText(getContext(), "Payment Success "+e.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }


    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(getContext(), "Payment Error "+s, Toast.LENGTH_SHORT).show();
    }
}