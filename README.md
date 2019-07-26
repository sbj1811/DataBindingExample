# DataBindingExample

Practice app that displays user medical info and a list of medication events. It also allows the user to input new events.

## Features

* The application uses a network client to pull down the JSON data for use in the application on startup and use the data to prefill the event list.
* Each event list item should display the following:
	* Event id
	* Medication name
	* Medication type
	* Timestamp
* The event list should be scrollable and sorted by date with the most recent event appearing at the top of the list.
* The app lets input a new event and display it in the existing list.
* Input fields includes the following:
	* Medication name
	* Timestamp
	* Medication type is derived from the medication name that is selected.
* New events do not persist between app sessions.

###### Sample tests for StringUtils and User database included.