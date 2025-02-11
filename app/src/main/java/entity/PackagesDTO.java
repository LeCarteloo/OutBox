package main.java.entity;

public class PackagesDTO {

    private final int packagesId;
    private final String packageNumber;
    private final String name;
    private final String surname;
    private final String recipentName;
    private final String voivodeship;
    private int userInfosId;
    private String timeOfPlannedDelivery;
    private String phoneNumber;
    private String streetAndNumber;
    private String city;
    private String status;
    private String additionalComment;
    private String email;
    private String sizeName;
    private String recipentSurname;

    public PackagesDTO(int userInfosId, int packagesId, String packageNumber, String timeOfPlannedDelivery,
                       String name, String surname, String phoneNumber, String streetAndNumber, String city,
                       String status, String additionalComment, String email, String voivodeship, String recipentName) {
        this.userInfosId = userInfosId;
        this.packagesId = packagesId;
        this.packageNumber = packageNumber;
        this.timeOfPlannedDelivery = timeOfPlannedDelivery;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.streetAndNumber = streetAndNumber;
        this.city = city;
        this.status = status;
        this.additionalComment = additionalComment;
        this.email = email;
        this.voivodeship = voivodeship;
        this.recipentName = recipentName;
    }

    public PackagesDTO(int packagesId, String packageNumber, String name, String surname, String status,
                       String sizeName,
                       String recipentName, String recipentSurname, String voivodeship) {
        this.packagesId = packagesId;
        this.packageNumber = packageNumber;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.sizeName = sizeName;
        this.recipentName = recipentName;
        this.recipentSurname = recipentSurname;
        this.voivodeship = voivodeship;
    }

    public int getUserInfosId() {
        return userInfosId;
    }

    public int getPackagesId() {
        return packagesId;
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    public String getTimeOfPlannedDelivery() {
        return timeOfPlannedDelivery;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public String getEmail() {
        return email;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getRecipentName() {
        return recipentName;
    }

    public String getRecipentSurname() {
        return recipentSurname;
    }

    public String getVoivodeship() {
        return voivodeship;
    }
}
