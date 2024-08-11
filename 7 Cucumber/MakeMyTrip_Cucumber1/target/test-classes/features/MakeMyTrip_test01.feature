Feature: Search Flights on MakeMyTrip

  Scenario: User searches for round-trip flights
    Given the user is on the MakeMyTrip homepage
    When the user selects "Flights" and "Round Trip"
    And the user enters "HYD" as the From location and "MAA" as the To location
    And the user selects the departure date and return date
    And the user clicks on the Search button
    Then the search results page is displayed
