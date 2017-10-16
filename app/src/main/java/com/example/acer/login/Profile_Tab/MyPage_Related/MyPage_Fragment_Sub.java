package com.example.acer.login.Profile_Tab.MyPage_Related;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Login_Related.LoginActivity;
import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.Profile_Tab.MyPage_Fragment;
import com.example.acer.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class MyPage_Fragment_Sub extends Fragment {

    TextView nameView,mtextView1, mtextView2, mtextView3;
    String name, birthday, email, user_imagePath;
    String HttpUrl = "http://104.198.211.126/insertUserimgUri.php";
    String httpUrl2 = "http://104.198.211.126/getUserimgUri.php";
    ImageView user_profile;

    ProgressDialog progressDialog;
    RequestQueue requestQueue, queue;

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    //private static final int PICK_FROM_IMAGE = 2;

    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 2;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage_sub, container, false);

        //발리 준비작업
        requestQueue = Volley.newRequestQueue(rootView.getContext());
        queue = Volley.newRequestQueue(rootView.getContext());
        progressDialog = new ProgressDialog(rootView.getContext());


        nameView = (TextView)rootView.findViewById(R.id.textView);
        mtextView1 = (TextView)rootView.findViewById(R.id.textView7);
        mtextView2 = (TextView)rootView.findViewById(R.id.textView9);
        mtextView3 = (TextView)rootView.findViewById(R.id.textView11);

        user_profile = (ImageView)rootView.findViewById(R.id.user_profile);

        user_imagePath = SharedPrefManager.getInstance(getActivity().getApplication()).getKeyUserImg();
        name = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUsername();
        birthday = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUserBirthday();
        email = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUserEmail();

        //이미지 셋팅
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(user_imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (exif != null) {
            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int exifDegree = exifOrientationToDegrees(exifOrientation);

            Bitmap bitmap = BitmapFactory.decodeFile(user_imagePath);//경로를 통해 비트맵으로 전환

            try {
                user_profile.setImageBitmap(rotate(bitmap, exifDegree));
            }//이미지 뷰에 비트맵 넣기
            catch (Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }

        nameView.setText(name);
        mtextView1.setText(name);
        mtextView2.setText(birthday);
        mtextView3.setText(email);


        ImageButton back_btn = (ImageButton)rootView.findViewById(R.id.back_btn);
        ImageButton deleteButton = (ImageButton)rootView.findViewById(R.id.deleteButton);
        ImageButton logoutButton = (ImageButton)rootView.findViewById(R.id.logoutButton);
        ImageButton photoButton = (ImageButton)rootView.findViewById(R.id.photoButton);

        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment swichFrag = new MyPage_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, swichFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getActivity().getApplicationContext()).logout();
                startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(), "회원탈퇴에 성공했습니다.", Toast.LENGTH_LONG).show();
                SharedPrefManager.getInstance(getActivity().getApplicationContext()).logout();
                startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
            }
        });

        photoButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                int permissionReadStorage = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
                int permissionWriteStorage = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permissionReadStorage == PackageManager.PERMISSION_DENIED || permissionWriteStorage == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions((Activity) getActivity().getApplicationContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
                }


                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int permissionCamera = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CAMERA);
                        if(permissionCamera == PackageManager.PERMISSION_DENIED) {
                            ActivityCompat.requestPermissions((Activity) getActivity().getApplicationContext(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                        }

                        doTakePhotoAction();
                    }
                };

                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        doTakeAlbumAction();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new AlertDialog.Builder(getContext())
                        .setTitle("업로드할 이미지 선택")
                        .setPositiveButton("사진촬영", cameraListener)
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
            }
        });


        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CAMERA:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.CAMERA)) {
                        if(grantResult == PackageManager.PERMISSION_GRANTED) {
                            //resultText.setText("camera permission authorized");
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "camera permission denied", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            case REQUEST_EXTERNAL_STORAGE:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if(grantResult == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getActivity().getApplicationContext(), "read/write storage permission authorized", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "read/write storage permission denied", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
        }
    }


    public void doTakePhotoAction() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        /*String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);*/

        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    public void doTakeAlbumAction() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        //intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        //startActivityForResult(intent, PICK_FROM_ALBUM);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_ALBUM);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode != RESULT_OK)
            return;

        switch (requestCode) {
            case PICK_FROM_ALBUM: {
                /*mImageCaptureUri = data.getData();
                Log.d("SmartWheel", mImageCaptureUri.getPath().toString());*/
                try{
                    SendPicture(data);
                }
                catch (Exception e) { Toast.makeText(getActivity().getApplicationContext(), "얘아니네", Toast.LENGTH_LONG).show();}

                break;
            }
            case PICK_FROM_CAMERA: {
                /*Intent intent = new Intent("com.android.camera.actio.CROP");
                intent.setDataAndType(mImageCaptureUri, "image*//*");

                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 200);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                //startActivityForResult(intent, CROP_FROM_IMAGE);*/
                SendPicture(data);
                break;
            }

        }
    }

    private void SendPicture(Intent data) {

        Uri imgUri = data.getData();
        final String imagePath = getRealPathFromURI(imgUri); // path 경로

        //사진 uri 디비로 쏘기
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity().getApplicationContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("userimg", imagePath);
                return params;
            }
        };
        requestQueue.add(stringRequest);

        //디비에서 사진 가져오기
        StringRequest request2 = new StringRequest(Request.Method.POST, httpUrl2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        try {
                            JSONObject  jsonobject=new JSONObject(ServerResponse);
                            JSONArray  jsonarray=jsonobject.getJSONArray("user");
                            JSONObject data = jsonarray.getJSONObject(0);
                            email = data.getString("email");
                            user_imagePath = data.getString("userimg");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity().getApplicationContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                return params;
            }
        };
        requestQueue.add(request2);

        ExifInterface exif = null;
        try {
            exif = new ExifInterface(user_imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (exif != null) {
            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int exifDegree = exifOrientationToDegrees(exifOrientation);

            Bitmap bitmap = BitmapFactory.decodeFile(user_imagePath);//경로를 통해 비트맵으로 전환

            try {
                user_profile.setImageBitmap(rotate(bitmap, exifDegree));
            }//이미지 뷰에 비트맵 넣기
            catch (Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }

        //프래그먼트 리프레쉬
        Fragment mypage2 = new MyPage_Fragment_Sub();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(mypage2);
        transaction.attach(mypage2);
        transaction.commit();
        Toast.makeText(getActivity().getApplicationContext(), "이미지 선택 완료!", Toast.LENGTH_LONG).show();
    }

    public Bitmap rotate(Bitmap src, float degree) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }


    public int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);  //managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }





}
