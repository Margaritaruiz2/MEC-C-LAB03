import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Temporizador {

    private static Timer timer;
    private static int tiempo;
    private static boolean alarma;

    public static void main(String[] args) {
        tiempo = 120000; // tiempo en milisegundos (2 minutos)
        alarma = true;

        timer = new Timer();
        TimerTask tarea;
        tarea = new TimerTask() {
            int segundos = 0;

            @Override
            public void run() {
                segundos++;
                mostrarHora(segundos);

                if (segundos == tiempo / 1000) {
                    timer.cancel();
                    if (alarma) {
                        generarAlarma();
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(tarea, 0, 1000);
    }

    public static void mostrarHora(int segundos) {
        Date date = new Date(segundos * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String hora = sdf.format(date);
        System.out.println(hora);
    }

    public static void generarAlarma() {
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                System.out.println("ALARMA");
            }
        };
        timer.scheduleAtFixedRate(tarea, tiempo, 10000);
    }
}
