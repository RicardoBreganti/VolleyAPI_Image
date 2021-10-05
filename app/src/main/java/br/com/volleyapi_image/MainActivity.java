package br.com.volleyapi_image;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class MainActivity extends AppCompatActivity {

    Button btnCarregar;
    ImageView imgServer;
    String Server = "http:192.168.15.3/VolleyAPI/Hackers.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCarregar = findViewById(R.id.btnCarregar);
        imgServer = findViewById(R.id.imgSever);

        btnCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ImageRequest imageRequest = new ImageRequest(Server,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                imgServer.setImageBitmap(response);
                            }
                        }, 0, 0,
                        ImageView.ScaleType.CENTER_CROP, null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),
                                        "Erro ao visualizar a imagem",
                                        Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        });
                MySingleton.getInstance(getApplicationContext()).addToRequestQue(imageRequest);
            }
        });


    }
}