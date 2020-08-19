package com.example.studentapplication;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvName, tvClass, tvSection, tvSchoolName, tvGender, tvDob, tvBloodGroup, tvFatherName, tvMotherName, tvParentsContactNo,
            tvAdd1, tvAdd2, tvCity, tvState, tvZip, tvEmergencyContNo, tvLocation;

    CircleImageView cvDetailProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        cvDetailProfile = findViewById(R.id.civ_detail_profile);
        tvName = findViewById(R.id.tv_profile_name_val);
        tvClass = findViewById(R.id.tv_class_name_val);
        tvSection = findViewById(R.id.tv_section_name_val);
        tvSchoolName = findViewById(R.id.tv_school_name_val);
        tvGender = findViewById(R.id.tv_gender_name_val);
        tvDob = findViewById(R.id.tv_dob_name_val);
        tvBloodGroup = findViewById(R.id.tv_blood_group_name_val);
        tvFatherName = findViewById(R.id.tv_father_name_val);
        tvMotherName = findViewById(R.id.tv_mother_name_val);
        tvParentsContactNo = findViewById(R.id.tv_parent_contact_no_val);
        tvAdd1 = findViewById(R.id.tv_address1_name_val);
        tvAdd2 = findViewById(R.id.tv_address2_name_val);
        tvCity = findViewById(R.id.tv_city_name_val);
        tvState = findViewById(R.id.tv_state_name_val);
        tvZip = findViewById(R.id.tv_zip_name_val);
        tvEmergencyContNo = findViewById(R.id.tv_emergency_contact_name_val);
        tvLocation = findViewById(R.id.tv_location_name_val);

        String name = getIntent().getExtras().getString("name");
        String classNo = getIntent().getExtras().getString("class");
        String section = getIntent().getExtras().getString("section");
        String schoolName = getIntent().getExtras().getString("schoolName");
        String gender = getIntent().getExtras().getString("gender");
        String dob = getIntent().getExtras().getString("dob");
        String bloodGroup = getIntent().getExtras().getString("bloodGroup");
        String fatherName = getIntent().getExtras().getString("fatherName");
        String motherName = getIntent().getExtras().getString("motherName");
        String parentsContactNo = getIntent().getExtras().getString("parentContactNo");
        String add1 = getIntent().getExtras().getString("add1");
        String add2 = getIntent().getExtras().getString("add2");
        String city = getIntent().getExtras().getString("city");
        String state = getIntent().getExtras().getString("state");
        String zip = getIntent().getExtras().getString("zip");
        String emergencyContactNo = getIntent().getExtras().getString("emergencyContactNo");
        String location = getIntent().getExtras().getString("location");

        Bitmap bitmap = (Bitmap) getIntent().getExtras().get("image");
        cvDetailProfile.setImageBitmap(bitmap);
        tvName.setText(name);
        tvClass.setText(classNo);
        tvSection.setText(section);
        tvSchoolName.setText(schoolName);
        tvGender.setText(gender);
        tvDob.setText(dob);
        tvBloodGroup.setText(bloodGroup);
        tvFatherName.setText(fatherName);
        tvMotherName.setText(motherName);
        tvParentsContactNo.setText(parentsContactNo);
        tvAdd1.setText(add1);
        tvAdd2.setText(add2);
        tvCity.setText(city);
        tvState.setText(state);
        tvZip.setText(zip);
        tvEmergencyContNo.setText(emergencyContactNo);
        tvLocation.setText(location);


    }
}