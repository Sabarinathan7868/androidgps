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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;

@SuppressWarnings("ALL")
public class AddDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tvDob, tvLocation;
    EditText etName, etSchoolName, etBloodGroup, etFatherName, etMotherName, etParentsContactNo, etAddress1, etAddress2, etCity, etState, etZip, etEmergencyContactNo;
    DatePickerDialog datePickerDialog;
    Spinner spClassList, spSectionList;
    Button btnAddData;
    CircleImageView civEducationIcon, civProfileEditIcon, cvProfilePicture;
    RadioGroup radioGroup;
//    RadioButton genderRadioButton;
RadioButton genderRadioButton, female;
    Integer REQUEST_CAMERA = 0;
    int PLACE_PICKER_REQUEST = 1;
    String lat = "";
    String longs = "";
    DatabaseHelper addDataDb;

    String[] listClass = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    String[] section = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    byte[] byteArray = null;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        tvDob = findViewById(R.id.tv_dob);
        tvLocation = findViewById(R.id.tv_add_location);
        spClassList = findViewById(R.id.sp_class_list);
        spSectionList = findViewById(R.id.sp_section_list);
        civProfileEditIcon = findViewById(R.id.civ_profile_edit_icon);
        cvProfilePicture = findViewById(R.id.civ_edit_profile);
        etName = findViewById(R.id.et_name);
        genderRadioButton  = findViewById(R.id.rb_male);
        female = findViewById(R.id.rb_female);
        etSchoolName = findViewById(R.id.et_school_name);
        etBloodGroup = findViewById(R.id.et_blood_group);
        etFatherName = findViewById(R.id.et_father_name);
        etMotherName = findViewById(R.id.et_mother_name);
        radioGroup = findViewById(R.id.radio_group);
        etParentsContactNo = findViewById(R.id.et_parents_contact_no);
        etAddress1 = findViewById(R.id.et_address_1);
        etAddress2 = findViewById(R.id.et_address_2);
        etCity = findViewById(R.id.et_city);
        etState = findViewById(R.id.et_state);
        etZip = findViewById(R.id.et_zip);
        etEmergencyContactNo = findViewById(R.id.et_emergency_contact_no);
        btnAddData = findViewById(R.id.btn_submit);
        spClassList.setOnItemSelectedListener(this);
        spSectionList.setOnItemSelectedListener(this);

        addDataDb = new DatabaseHelper(this);


        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO add data into db
                String name = etName.getText().toString();
                String schoolName = etSchoolName.getText().toString();
                String dob = tvDob.getText().toString();
                String classNo = spClassList.getSelectedItem().toString();
                String section = spSectionList.getSelectedItem().toString();
                String bloodGroup = etBloodGroup.getText().toString();
                String fatherName = etFatherName.getText().toString();
                String motherName = etMotherName.getText().toString();
                String parentsContactNo = etParentsContactNo.getText().toString();
                String address1 = etAddress1.getText().toString();
                String address2 = etAddress2.getText().toString();
                String city = etCity.getText().toString();
                String state = etState.getText().toString();
                String zip = etZip.getText().toString();
                String emergencyContactNo = etEmergencyContactNo.getText().toString();
                String addLocation = tvLocation.getText().toString();
                int selectedId = radioGroup.getCheckedRadioButtonId();

                String radioGroup = selectedId == genderRadioButton.getId() ? "Male": (selectedId == female.getId() ? "Female":"");

               /* Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                cvProfilePicture.setImageBitmap(bmp);*/


                if (name.equals("") || schoolName.equals("") || dob.equals("") || bloodGroup.equals("") || fatherName.equals("") || motherName.equals("")
                        || parentsContactNo.equals("") || address1.equals("") || address2.equals("") || city.equals("") || state.equals("") || zip.equals("") ||
                        emergencyContactNo.equals("") || addLocation.equals("") || byteArray == null || genderRadioButton.equals("")) {
                    Toast.makeText(AddDataActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean addData = addDataDb.addDataInsert(name,radioGroup, classNo, section, schoolName, dob, bloodGroup, fatherName,
                            motherName, parentsContactNo, address1, address2, city, state, zip, emergencyContactNo, addLocation, byteArray,lat, longs);
                    if (addData == true) {
                        Boolean addDataInsert = addDataDb.addDataInsert(name, radioGroup, classNo, section, schoolName, dob, bloodGroup, fatherName,
                                motherName, parentsContactNo, address1, address2, city, state, zip, emergencyContactNo, addLocation, byteArray, lat, longs);
                        if (addDataInsert == true) {

                            etName.getText().clear();
                            etSchoolName.getText().clear();
                            etBloodGroup.getText().clear();
                            etFatherName.getText().clear();
                            etMotherName.getText().clear();
                            etParentsContactNo.getText().clear();
                            etAddress1.getText().clear();
                            etAddress2.getText().clear();
                            etCity.getText().clear();
                            etState.getText().clear();
                            etZip.getText().clear();
                            etEmergencyContactNo.getText().clear();
                            tvLocation.setText("");
                            tvDob.setText("");

//                            Intent intent = new Intent(SignUpActivity.this, Sign_in.class);
//                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Data Registered Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


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

    public void onclickButtonMethod(View v) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        genderRadioButton = (RadioButton) findViewById(selectedId);
        if (selectedId == -1) {
            Toast.makeText(AddDataActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddDataActivity.this, genderRadioButton.getText(), Toast.LENGTH_SHORT).show();
        }

    }

    private void pickLocation() {
        //TODO pick location
//        Intent intent = new Intent(AddDataActivity.this, AddLocationActivity.class);
//        startActivity(intent);
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
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
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                cvProfilePicture.setImageBitmap(bmp);
            }
        }


        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Place place = PlacePicker.getPlace(this, data);
//                String address = String.format("Place @s",place.getAddress());
//                tvLocation.setText(address);
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());
                StringBuilder stringBuilder = new StringBuilder();

                try {
                    this.lat = place.getLatLng().latitude +"";
                    this.longs = place.getLatLng().longitude+"";
                    addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    stringBuilder.append(address);
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                    tvLocation.setText(city + state + country + postalCode + knownName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                tvLocation.setText(stringBuilder.toString());
            }
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}