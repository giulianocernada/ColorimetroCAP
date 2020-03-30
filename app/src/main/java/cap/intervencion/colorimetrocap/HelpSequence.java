package cap.intervencion.colorimetrocap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class HelpSequence extends AppCompatActivity implements View.OnClickListener {
    private ImageButton hCameraButton;
    private ImageButton hLogButton;
    private ImageButton hInfoButton;

    private ImageButton hHelpButton;
    private Button hCloseButton;

    private static final String SHOWCASE_ID = "sequence example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_sequence);
        hCameraButton = findViewById(R.id.helpcamerabutton);
        hCameraButton.setOnClickListener(this);

        hLogButton = findViewById(R.id.helpLogButton);
        hLogButton.setOnClickListener(this);

        hInfoButton = findViewById(R.id.helpInfoButton);
        hInfoButton.setOnClickListener(this);

        hHelpButton = findViewById(R.id.helpHelpButton);
        hHelpButton.setOnClickListener(this);

        hCloseButton = findViewById(R.id.helpCloseButton);
        hCloseButton.setOnClickListener(this);

        presentShowcaseSequence(); // one second delay
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.helpcamerabutton || v.getId() == R.id.helpLogButton || v.getId() == R.id.helpInfoButton) {

            presentShowcaseSequence();

        } else if (v.getId() == R.id.helpCloseButton) {

            MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID);
            finish();
        }

    }

    private void presentShowcaseSequence() {

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

//        sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
//            @Override
//            public void onShow(MaterialShowcaseView itemView, int position) {
//            }
//        });

        sequence.setConfig(config);

        sequence.addSequenceItem(hCameraButton, "Acceder a la cámara para tomar muestra", "OK");

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(hLogButton)
                        .setDismissText("OK")
                        .setContentText("Acceder al registro de muestras")
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(hInfoButton)
                        .setDismissText("OK")
                        .setContentText("Visitar el sitio web de la CAP")
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(hHelpButton)
                        .setDismissText("OK")
                        .setContentText("Acceder a este tutorial")
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(hCloseButton)
                        .setDismissText("OK")
                        .setContentText("Cerrar ayuda")
                        .withRectangleShape()
                        .build()
        );
        sequence.start(); }
}
