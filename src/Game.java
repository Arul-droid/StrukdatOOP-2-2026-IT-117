public class Game {
    private String title;
    private String publisher;
    private double playTime;
    private double percentKomplit;

    public Game(String title, String publisher, double playTime, double percentKomplit) {
        this.title = title;
        this.publisher = publisher;
        this.playTime = playTime;
        this.percentKomplit = percentKomplit;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getplayTime() {
        return playTime;
    }

    public double getpercentKomplit() {
        return percentKomplit;
    }

    public String getStatus() {
        if (percentKomplit == 100.0) {
            return "Selesai";
        } else if (percentKomplit >= 50.0) {
            return "Sedang Dimainkan";
        } else {
            return "Baru Dimulai";
        }
    }

    public void displayInfo() {
        System.out.println("Judul       : " + title);
        System.out.println("Publisher   : " + publisher);
        System.out.println("Waktu Main  : " + playTime + " jam");
        System.out.println("Komplisi   : " + percentKomplit + "%");
        System.out.println("Status      : " + getStatus());
    }
}
