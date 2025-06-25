package ar.utn.ba.ddsi.mailing.models.entities.Clima;


public class Temperatura {
    private final double celsius;

    public Temperatura(double celsius) {
        this.celsius = celsius;
    }

    public double enCelsius() {
        return celsius;
    }

    public double enFahrenheit() {
        return celsius * 9 / 5 + 32;
    }
}
