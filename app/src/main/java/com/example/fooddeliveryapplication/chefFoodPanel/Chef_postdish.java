package com.example.fooddeliveryapplication.chefFoodPanel;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddeliveryapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


public class Chef_postdish extends AppCompatActivity {

    ImageButton imageButton;
    Button post_dish;
    TextInputLayout desc,qty,pri;
    String description,quantity,price;
    Uri imageuri;
    private Uri mcropimage;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,dataa;
    FirebaseAuth Fauth;
    StorageReference ref;
    String ChefId, RandomUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_postdish);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        desc = (TextInputLayout) findViewById(R.id.description);
        qty = (TextInputLayout) findViewById(R.id.Quantity);
        pri = (TextInputLayout) findViewById(R.id.price);
        post_dish = (Button) findViewById(R.id.post);
        Fauth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference("FoodDetails");

        try{
            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            dataa = firebaseDatabase.getInstance().getReference("Chef").child(userid);
            dataa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Chef cheff = snapshot.getValue(Chef.class);
                    imageButton = (ImageButton) findViewById(R.id.image_upload);

                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onSelectImageClick(v);
                        }
                    });
                    post_dish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            description = desc.getEditText().getText().toString().trim();
                            quantity = qty.getEditText().getText().toString().trim();
                            price = pri.getEditText().getText().toString().trim();

                            if(isValid()){
                                uploadImage();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){
            Log.e("Error: ", e.getMessage());
        }

    }

    private void uploadImage() {

        if(imageuri != null){
            final ProgressDialog progressDialog = new ProgressDialog(Chef_postdish.this);
            progressDialog.setTitle("Uploading.....");
            progressDialog.show();
            RandomUID = UUID.randomUUID().toString();
            ref = storageReference.child(RandomUID);
            ChefId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            FoodDetails info = new FoodDetails(quantity,price,description,String.valueOf(uri),RandomUID,ChefId, ChefId);
                            firebaseDatabase.getInstance().getReference("FoodDetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUID)
                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            progressDialog.dismiss();
                                            Toast.makeText(Chef_postdish.this,"Dish Posted Successfully!",Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(Chef_postdish.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded "+(int) progress+"%");
                    progressDialog.setCanceledOnTouchOutside(false);
                }
            });
        }

    }

    private boolean isValid() {

        desc.setErrorEnabled(false);
        desc.setError("");
        qty.setErrorEnabled(false);
        qty.setError("");
        pri.setErrorEnabled(false);
        pri.setError("");

        boolean isValidDescription = false,isValidPrice = false, isValidQuantity = false, isValid = false;

        if (TextUtils.isEmpty(description)){
            desc.setErrorEnabled(true);
            desc.setError("Description is required");
        }else{
            desc.setError(null);
            isValidDescription=true;
        }
        if (TextUtils.isEmpty(quantity)){
            desc.setErrorEnabled(true);
            desc.setError("Enter number of plates or items");
        }else{
            desc.setError(null);
            isValidQuantity=true;
        }
        if (TextUtils.isEmpty(price)){
            desc.setErrorEnabled(true);
            desc.setError("Please Mention Price");
        }else{
            desc.setError(null);
            isValidPrice=true;
        }
        isValid = (isValidDescription && isValidQuantity && isValidPrice)?true:false;
        return isValid;
    }

    private void startCropImageActivity(Uri imageuri){
//        CropImage.activity(imageuri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setMultiTouchEnabled(true)
//                .start(this);
    }
    private void onSelectImageClick(View v){
//        CropImage.startPickImageActivity(this);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mcropimage != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCropImageActivity(mcropimage);
        } else {
            Toast.makeText(this, "Cancelling Permission Not Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

//        if (requestCode==CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode== Activity.RESULT_OK){
//            imageuri = CropImage.getPickImageResultUri(this,data);
//            if(CropImage.isReadExternalStoragePermissionRequired(this,imageuri)){
//                mcropimage = imageuri;
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
//            }else{
//                startCropImageActivity(imageuri);
//            }
//        }
//        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if(resultCode==RESULT_OK){
//                ((ImageButton) findViewById(R.id.image_upload)).setImageURI(result.getUri);
//                Toast.makeText(this, "Cropped Successfully", Toast.LENGTH_SHORT).show();
//            } else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
//                Toast.makeText(this,"Failed To Crop"+result.getError(),Toast.LENGTH_SHORT).show();
//            }
//        }


            super.onActivityResult(requestCode, resultCode, data);
    }
}