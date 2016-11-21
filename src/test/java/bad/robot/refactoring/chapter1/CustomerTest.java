package bad.robot.refactoring.chapter1;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    private static final Movie THE_HULK = new Movie("The Hulk", Movie.CHILDREN);
    private static final Movie IRON_MAN = new Movie("Iron Man 4", Movie.NEW_RELEASE);
    private static final Movie SPIDER_MAN = new Movie("Spiderman", Movie.REGULAR);

    private final Customer customer = new Customer("fred");

    @Test
    public void basicChildrenRental() {
        String expectedResult = expectedMessageFor("The Hulk", 1.5, 1.5, 1);
        customer.addRental(new Rental(THE_HULK, 2));
        assertEquals(customer.statement(), expectedResult);
    }

    @Test
    public void shouldDiscountChildrensRentals() {
        String expectedResult = expectedMessageFor("The Hulk", 3.0, 3.0, 1);
        customer.addRental(new Rental(THE_HULK, 4));
        assertEquals(customer.statement(), expectedResult);
    }

    @Test
    public void basicNewReleaseRental() {
        String expectedResult = expectedMessageFor("Iron Man 4", 3.0, 3.0, 1);
        customer.addRental(new Rental(IRON_MAN, 1));
        assertEquals(customer.statement(), expectedResult);
    }

    @Test
    public void shouldNotDiscountNewReleaseRentalsButBonusFrequentRenterPoints() {
        String expectedResult = expectedMessageFor("Iron Man 4", 12.0, 12.0, 2);
        customer.addRental(new Rental(IRON_MAN, 4));
        assertEquals(customer.statement(), expectedResult);
    }

    @Test
    public void basicRegularRental() {
        String expectedResult = expectedMessageFor("Spiderman", 2.0, 2.0, 1);
        customer.addRental(new Rental(SPIDER_MAN, 2));
        assertEquals(customer.statement(), expectedResult);
    }

    @Test
    public void shouldDiscountRegularRental() {
        String expectedResult = expectedMessageFor("Spiderman", 5.0, 5.0, 1);
        customer.addRental(new Rental(SPIDER_MAN, 4));
        assertEquals(customer.statement(), expectedResult);
    }

    @Test
    public void shouldSumVariousRentals() {
        String expectedResult = "Rental record for fred\n\tThe Hulk\t1.5\n\tSpiderman\t2.0\n\tIron Man 4\t9.0\nAmount owed is 12.5\nYou earned 4 frequent renter points";
        customer.addRental(new Rental(THE_HULK, 2));
        customer.addRental(new Rental(SPIDER_MAN, 1));
        customer.addRental(new Rental(IRON_MAN, 3));
        assertEquals(customer.statement(), expectedResult);
    }

    private static String expectedMessageFor(String rental, double price, double total, int renterPointsEarned) {
        return "Rental record for fred\n\t" + rental + "\t" + price + "\nAmount owed is " + total + "\nYou earned " + renterPointsEarned + " frequent renter points";
    }

}
