package com.borjabolufer.tema03.ejercicio04;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random rnd = new Random();
    private ImageView imagen, imagenCPU;
    private Button bPapel, bPiedra, bTijera, bReiniciar;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bPapel = findViewById(R.id.bPapel);
        bPiedra = findViewById(R.id.bPiedra);
        bTijera = findViewById(R.id.bTijera);
        bReiniciar = findViewById(R.id.bReiniciar);
        imagen = findViewById(R.id.iQuest);
        imagenCPU = findViewById(R.id.iQuestDos);

        resultado = findViewById(R.id.tResultado);

        bPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jugar("papel");  // Usuario elige "papel"
            }
        });

        bPiedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jugar("piedra"); // Usuario elige "piedra"
            }
        });

        bTijera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jugar("tijera"); // Usuario elige "tijera"
            }
        });

        // Al hacer clic en el botón de reiniciar, se resetea la imagen y botones
        bReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarJuego();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private int randomNumber() {
        return rnd.nextInt(3) + 1; // 1, 2 o 3
    }



    private void jugar(String eleccionUsr) {
        // Generar un nuevo número aleatorio para la CPU en cada jugada
        int numCPU = randomNumber();

        // Establecer la imagen correspondiente a la elección del jugador
        switch (eleccionUsr) {
            case "papel":
                imagen.setImageResource(R.drawable.paper); // Cambia la imagen del jugador
                break;
            case "piedra":
                imagen.setImageResource(R.drawable.pedra); // Cambia la imagen del jugador
                break;
            case "tijera":
                imagen.setImageResource(R.drawable.tisores); // Cambia la imagen del jugador
                break;
        }

        // Establecer la imagen correspondiente a la elección de la CPU
        setImagenCPU(numCPU);

        // Comparar las elecciones y mostrar el resultado
        if (numCPU == 1 && eleccionUsr.equals("papel") ||
                numCPU == 2 && eleccionUsr.equals("piedra") ||
                numCPU == 3 && eleccionUsr.equals("tijera")) {
            mostrarResultado("Has empatado!!!");
        } else if (numCPU == 1 && eleccionUsr.equals("piedra") ||
                numCPU == 2 && eleccionUsr.equals("tijera") ||
                numCPU == 3 && eleccionUsr.equals("papel")) {
            mostrarResultado("Has perdido");
        } else {
            mostrarResultado("Has ganado!!");
        }

        // Desactivar los botones después de jugar
        deshabilitarBotones();
        bReiniciar.setVisibility(View.VISIBLE); // Mostrar botón de reinicio
    }


    private void mostrarResultado(String mensaje) {
        resultado.setText(mensaje);
    }

    private void setImagenCPU(int num) {
        switch (num) {
            case 1:
                imagenCPU.setImageResource(R.drawable.paper);
                break;
            case 2:
                imagenCPU.setImageResource(R.drawable.pedra);
                break;
            case 3:
                imagenCPU.setImageResource(R.drawable.tisores);
                break;
            default:
                Toast.makeText(MainActivity.this, "Ha fallado el programa", Toast.LENGTH_SHORT).show();
        }
    }

    private void deshabilitarBotones() {
        bPapel.setEnabled(false);
        bPiedra.setEnabled(false);
        bTijera.setEnabled(false);
    }

    private void habilitarBotones() {
        bPapel.setEnabled(true);
        bPiedra.setEnabled(true);
        bTijera.setEnabled(true);
    }

    private void reiniciarJuego() {
        // Restablece el estado del juego
        imagen.setImageResource(R.drawable.question); // Restablecer imagen del usuario
        imagenCPU.setImageResource(R.drawable.question); // Restablecer imagen de la CPU
        habilitarBotones();
        bReiniciar.setVisibility(View.GONE); // Ocultar botón de reinicio
        Toast.makeText(MainActivity.this, "Juego reiniciado", Toast.LENGTH_SHORT).show();
    }
}
