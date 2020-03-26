package cap.intervencion.colorimetrocap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Landing extends AppCompatActivity {
    ImageButton landingCameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        landingCameraButton = findViewById(R.id.landingcamerabutton);
        landingCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });
    }

    //IR AL SITIO WEB. ACA DECLARO EL METODO goToWebsite y en el archivo XML esta el onClick que lo inicia, asociado al boton info
    public void goToWebsite (View view) {
        goToUrl( "https://cooperativapampero.coop");
    }

    private void goToUrl(String url){
        Uri uriUrl = Uri.parse(url);
        Intent WebView = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(WebView);
    }

    //CAMARA
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void launchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
