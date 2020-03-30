package cap.intervencion.colorimetrocap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class Landing extends AppCompatActivity implements View.OnClickListener {
    ImageButton landingCameraButton;
    ImageButton landingHelpButton;
    ImageButton landingInfoButton;
    ImageButton landingLogButton;

    //A PARTIR DE ACÁ
    private static final String SHOWCASE_ID = "sequence example";

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
        landingHelpButton = findViewById(R.id.landingHelpButton);
        landingHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentShowcaseSequence();
            }
        });
        landingLogButton = findViewById(R.id.landingLogButton);
        landingInfoButton = findViewById(R.id.landingInfoButton);
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

    //SHOWCASE
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.helpcamerabutton || v.getId() == R.id.helpLogButton || v.getId() == R.id.helpInfoButton) {

            presentShowcaseSequence();
        }
    }

    private void presentShowcaseSequence() {

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

        sequence.setConfig(config);

        sequence.addSequenceItem(landingCameraButton, "Acceder a la cámara para tomar muestra", "OK");

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(landingLogButton)
                        .setDismissText("OK")
                        .setContentText("Acceder al registro de muestras")
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(landingInfoButton)
                        .setDismissText("OK")
                        .setContentText("Visitar el sitio web de la CAP")
                        .build()
        );
        sequence.start();
        MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID);
        }
}