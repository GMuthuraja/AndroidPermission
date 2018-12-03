package app.example.app.androidpermission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.Permission;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ArrayList<String> permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.requestPermissonButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int externalStorage = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                    int accessCamera = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
                    permissions = new ArrayList();

                    if (externalStorage == PackageManager.PERMISSION_DENIED) {
                        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                    } if(accessCamera == PackageManager.PERMISSION_DENIED){
                        permissions.add(Manifest.permission.CAMERA);
                    }

                    if(permissions.size() > 0) {
                        ActivityCompat.requestPermissions(MainActivity.this, permissions.toArray(new String[permissions.size()]), 1);
                    }else{
                        Toast.makeText(MainActivity.this,"All Permission Granted",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        if(requestCode == 1){
            for (int i=0; i<grantResults.length; i++){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this,"Oops you just denied the permission to "+permissions[i],Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
