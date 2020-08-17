package com.example.studentapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;

@SuppressWarnings("ALL")
public class AddDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tvDob, tvLocation;
    DatePickerDialog datePickerDialog;
    Spinner spClassList, spSectionList;
    CircleImageView civEducationIcon, civProfileEditIcon, cvProfilePicture;
    Integer REQUEST_CAMERA = 0;
    int PLACE_PICKER_REQUEST = 1;


    String[] listClass = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    String[] section = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        tvDob = findViewById(R.id.tv_dob);
        spClassList = findViewById(R.id.sp_class_list);
        spSectionList = findViewById(R.id.sp_section_list);
        spClassList.setOnItemSelectedListener(this);
        spSectionList.setOnItemSelectedListener(this);
        civProfileEditIcon = findViewById(R.id.civ_profile_edit_icon);
        cvProfilePicture = findViewById(R.id.civ_edit_profile);
        tvLocation = findViewById(R.id.tv_add_location);


        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickLocation();
            }
        });


        civProfileEditIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromCamera();
            }
        });

        //TODO For SpClassList

        ArrayAdapter classList = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listClass);
        classList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spClassList.setAdapter(classList);

        /*List<String> classList = new ArrayList<String>();
        for (int i = 0; i <= 12; i++) {
            classList.add("classList " + i);
        }
        ArrayAdapter<String> classDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, classList);
        classDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClassList.setAdapter(classDataAdapter);*/

        //TODO For SpSectionList

        ArrayAdapter sectionList = new ArrayAdapter(this, android.R.layout.simple_spinner_item, section);
        sectionList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spSectionList.setAdapter(sectionList);


        tvDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AddDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tvDob.setText(day + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }


    private void pickLocation() {
        //TODO pick location
//        Intent intent = new Intent(AddDataActivity.this, AddLocationActivity.class);
//        startActivity(intent);
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this),PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    private void pickImageFromCamera() {

        //TODO Camera
        final CharSequence[] items = {"Camera", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddDataActivity.this);
        builder.setTitle("Take photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePhoto, REQUEST_CAMERA);
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == REQUEST_CAMERA) {
                    Bundle bundle = data.getExtras();
                    final Bitmap bmp = (Bitmap) bundle.get("data");
                    cvProfilePicture.setImageBitmap(bmp);
                }
            }

            //TODO for location pick
            if (resultCode == RESULT_OK) {
            if (requestCode==1){
                Place place = PlacePicker.getPlace(this, data);
//                String address = String.format("Place @s",place.getAddress());
//                tvLocation.setText(address);
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    stringBuilder.append(address);
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tvLocation.setText(stringBuilder.toString());
                Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}