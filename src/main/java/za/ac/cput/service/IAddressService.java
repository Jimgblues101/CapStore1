package za.ac.cput.service;

import za.ac.cput.domain.Address;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * IAddressService defines the contract for address-related operations.
 * It extends the generic IService interface, providing specific methods
 * for managing {@link Address} entities. This includes finding addresses by
 * user ID, address line, zip code, country, phone number, and checking for
 * soft-deleted addresses.
 */
public interface IAddressService extends IService<Address, Long> {

    /**
     * Finds all Addresses associated with a given User.
     *
     * @param userId the User entity to search by
     * @return a list of Addresses associated with the given User
     */
    Optional<Address> findByUserId(Long userId);

    /**
     * Finds all Addresses with a given title.
     *
     * @param title the title to search by
     * @return a list of Addresses with the given title
     */
    List<Address> findByTitle(String title);

    /**
     * Finds all Addresses with a given address line 1.
     *
     * @param addressLine1 the address line 1 to search by
     * @return a list of Addresses with the given address line 1
     */
    List<Address> findByAddressLine1(String addressLine1);

    /**
     * Finds all Addresses with a given address line 2.
     *
     * @param addressLine2 the address line 2 to search by
     * @return a list of Addresses with the given address line 2
     */
    List<Address> findByAddressLine2(String addressLine2);

    /**
     * Finds all Addresses in a given country.
     *
     * @param country the country to search by
     * @return a list of Addresses in the given country
     */
    List<Address> findByCountry(String country);

    /**
     * Finds all Addresses in a given city.
     *
     * @param city the city to search by
     * @return a list of Addresses in the given city
     */
    List<Address> findByCity(String city);

    /**
     * Finds all Addresses with a given postal code.
     *
     * @param zipCode the postal code to search by
     * @return a list of Addresses with the given postal code
     */
    List<Address> findByPostalCode(String zipCode);

    /**
     * Finds all Addresses with a given phone number.
     *
     * @param phoneNumber the phone number to search by
     * @return a list of Addresses with the given phone number
     */
    List<Address> findByPhoneNumber(Integer phoneNumber);

    /**
     * Finds all Addresses created after a given date.
     *
     * @param createdAt the date to search by
     * @return a list of Addresses created after the given date
     */
    List<Address> findByCreatedAtAfter(LocalDate createdAt);

    /**
     * Finds all Addresses that have a non-null deletedAt field.
     *
     * @return a list of Addresses that have been marked as deleted
     */
    List<Address> findByUpdatedAt(LocalDate updatedAt);
}