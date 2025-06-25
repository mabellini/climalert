package ar.utn.ba.ddsi.mailing.models.entities.Clima;

public class VelocidadViento {
    private final double velocidadKmh;

    public VelocidadViento(double velocidadKmh) {
        this.velocidadKmh = velocidadKmh;
    }

    public double getVelocidadKmh() {
        return velocidadKmh;
    }

    public double getVelociddadMph(){
        return velocidadKmh*0.621371;
    }
}
