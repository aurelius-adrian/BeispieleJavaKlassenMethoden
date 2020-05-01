public class Auto {

    private String marke;
    private String name;
    private float maximaleGeschwindigkeit;
    private float maximaleLeistungPS;
    private float gewicht;
    private float hubraum;
    private float beschleunigung;

    Auto(String marke, String name, float maximaleGeschwindigkeit, float maximaleLeistungPS, float gewicht, float hubraum, float beschleunigung) {
        this.marke = marke;
        this.name = name;
        this.maximaleGeschwindigkeit = maximaleGeschwindigkeit;
        this.maximaleLeistungPS = maximaleLeistungPS;
        this.gewicht = gewicht;
        this.hubraum = hubraum;
        this.beschleunigung = beschleunigung;
    }

    public String getMarke() {
        return marke;
    }

    public String getName() {
        return name;
    }

    public float getMaximaleGeschwindigkeit() {
        return maximaleGeschwindigkeit;
    }

    public float getMaximaleLeistungPS() {
        return maximaleLeistungPS;
    }

    public float getGewicht() {
        return gewicht;
    }

    public float getHubraum() {
        return hubraum;
    }

    public float getBeschleunigung() {
        return beschleunigung;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaximaleGeschwindigkeit(float maximaleGeschwindigkeit) {
        this.maximaleGeschwindigkeit = maximaleGeschwindigkeit;
    }

    public void setMaximaleLeistungPS(float maximaleLeistungPS) {
        this.maximaleLeistungPS = maximaleLeistungPS;
    }

    public void setGewicht(float gewicht) {
        this.gewicht = gewicht;
    }

    public void setHubraum(float hubraum) {
        this.hubraum = hubraum;
    }

    public void setBeschleunigung(float beschleunigung) {
        this.beschleunigung = beschleunigung;
    }
}