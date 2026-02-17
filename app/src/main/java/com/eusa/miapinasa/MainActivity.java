package com.eusa.miapinasa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.URL;

/**
 * Clase principal de la aplicación.
 * Se encarga de la UI y de lanzar la petición a la API.
 */
public class MainActivity extends AppCompatActivity {

    // Componentes del layout activity_main.xml
    private TextView tvTitle, tvDate, tvExplanation;
    private ImageView ivNasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos los componentes buscando sus IDs
        tvTitle = findViewById(R.id.tvTitle);
        tvDate = findViewById(R.id.tvDate);
        tvExplanation = findViewById(R.id.tvExplanation);
        ivNasa = findViewById(R.id.ivNasa);

        // Ejecutamos la carga en un hilo secundario para evitar errores de red
        cargarDatosDeNasa();
    }

    private void cargarDatosDeNasa() {
        new Thread(() -> {
            try {
                // Instanciamos el cliente para obtener los datos JSON
                NasaApodClient client = new NasaApodClient();
                JSONObject data = client.getApodData();

                // Sacamos los strings del JSON de la NASA
                String title = data.getString("title");
                String date = data.getString("date");
                String explanation = data.getString("explanation");
                String imageUrl = data.getString("url");

                // DESCARGA NATIVA DE LA IMAGEN:
                // Abrimos conexión directa a la URL de la imagen y la decodificamos
                InputStream in = new URL(imageUrl).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);

                // Volvemos al hilo de la interfaz para pintar los datos
                runOnUiThread(() -> {
                    tvTitle.setText(title);
                    tvDate.setText(date);
                    tvExplanation.setText(explanation);
                    // Mostramos el bitmap descargado en el ImageView
                    ivNasa.setImageBitmap(bitmap);
                });

            } catch (Exception e) {
                // Si hay error (como falta de internet), sale por el log
                e.printStackTrace();
            }
        }).start();
    }
}