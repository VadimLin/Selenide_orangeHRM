package eu.senla.Utils.FakerUtil;

import com.github.javafaker.Faker;

public class FakerUtil {

  public String generateRandomTitle() {
    Faker faker = new Faker();
    return faker.job().title();
  }

  public String generateRandomFirstName() {
    Faker faker = new Faker();
    return faker.name().firstName();
  }

  public String generateRandomLastName() {
    Faker faker = new Faker();
    return faker.name().lastName();
  }

  public String generateRandomMiddleName() {
    Faker faker = new Faker();
    return faker.funnyName().name();
  }

  public String generateRandomFullName() {
    Faker faker = new Faker();
    return faker.name().firstName() + faker.funnyName().name() + faker.name().lastName();
  }

  public String generateRandomEmailAddress() {
    Faker faker = new Faker();
    return faker.internet().emailAddress();
  }

  public String generateRandomPhoneNumber() {
    Faker faker = new Faker();
    return faker.phoneNumber().phoneNumber();
  }

  public String generateRandomKeywords() {
    Faker faker = new Faker();
    final int words = 5;
    return faker.lorem().words(words).toString();
  }

  public String generateRandomNotes() {
    Faker faker = new Faker();
    return faker.lorem().sentence();
  }
}
