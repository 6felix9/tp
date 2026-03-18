---
layout: page
title: User Guide
---

## Pacebook

At its core, Pacebook helps running coaches like you to manage your runners’ contacts effectively. But it's also much 
more than that. It helps you keep track of runners’ data, such as their timings, training sessions, and emergency 
contacts. It also allows you to find your athletes easily and monitor their improvements over time. So, whether you are 
a private coach or training a professional running club, let Pacebook match your pace.

Pacebook is a **desktop application designed for running coaches to manage runners, training plans, and essential contact information efficiently**. It is optimized for use via a **Command Line Interface (CLI)**, enabling fast interaction while still providing a simple graphical interface.

Unlike generic contact management tools, Pacebook is tailored specifically for **training environments**, where quick access to structured runner data is critical for planning and execution.

---

## Target Users

Pacebook is designed primarily for:

### Run Club Coaches
- Coaches managing multiple runners within a team or club
- Responsible for planning and monitoring training sessions
- Require quick access to structured runner data

---

## Assumptions About Users

Pacebook assumes that users:

### Technical Assumptions
- Have **basic computer literacy**
- Are comfortable typing simple CLI commands (e.g. `add`, `edit`, `find`)
- Do not require programming knowledge
- Can understand structured input formats (e.g. `n/NAME`, `p/PHONE`)

### Domain Assumptions
Users are familiar with running-related training concepts, including:
- Competition distances (e.g. 42km marathon)
- Long run distances (e.g. 10km)
- Interval training formats (e.g. 10 × 400m)

### Usage Assumptions
- Users prefer **efficiency and speed** over graphical-heavy systems
- Users need to **quickly retrieve and update runner information**
- Users manage **multiple runners simultaneously**
- Users rely on structured data to **plan training sessions effectively**

---

## User Needs Addressed

Pacebook is designed to support the following core needs:

- Managing runners within a run club environment
- Recording key training details such as:
    - Competition distance (e.g. 42km)
    - Long run (e.g. 10km)
    - Interval training (e.g. 10 × 400m)
- Sorting runners based on:
    - Personal best
    - Availability
    - Club or team
    - Alphabetical order
- Adding and maintaining **emergency contact information**
- Searching runners efficiently, including **partial keyword matching**
- Updating runner details quickly using edit commands
- Accessing available commands via a `help` function

These features enable coaches to **plan training sessions more effectively, reduce administrative effort, and focus on improving runner performance**.

---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure that Java `17` or above is installed on your computer.<br>
   If you do not have Java installed, follow the installation guide for your operating system:
    * **Windows:** [Java installation guide for Windows](https://se-education.org/guides/tutorials/javaInstallationWindows.html).
    * **Mac:** [Java installation guide for Mac](https://se-education.org/guides/tutorials/javaInstallationMac.html).
    * **Linux:** [Java installation guide for Linux](https://se-education.org/guides/tutorials/javaInstallationLinux.html).

2. Download the latest `.jar` file from [here](https://github.com/AY2526S2-CS2103T-W14-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for Pacebook.

4. Open a command terminal and run the application using the following commands:
   ```
   cd [home folder]
   java -jar pacebook.jar
   ```
5. After a few seconds, the application window should appear, as shown below. The app includes sample data to help you get started.<br>
   ![Ui](images/Ui.png)

6. Type a command into the command box and press Enter to run it. For example, typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all athletes.

   * `addathlete n/John Doe a/21 p/98765432 e/johnd@example.com ad/John street, block 123, #01-01 d/01/01/2001` : Adds an athlete named `John Doe` to Pacebook.

   * `deleteathlete 3` : Deletes the 3rd athlete shown in the current list.

   * `clear` : Deletes all athletes.

   * `exit` : Exits the app.

7. See the [Features](#features) section below for the full list of commands and how to use them.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are values you need to provide.  
  For example, in `addathlete n/NAME`, `NAME` should be replaced with the athlete’s actual name, such as `addathlete n/John Doe`.

* Items in square brackets are optional.  
  For example, `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or just `n/John Doe`.

* Items followed by `…` can be used multiple times, including zero times.  
  For example, `[t/TAG]…` can be omitted completely, or used as `t/friend`, `t/friend t/team`, and so on.

* Parameters can be entered in any order.  
  For example, if the command format is `n/NAME p/PHONE_NUMBER`, then `p/PHONE_NUMBER n/NAME` also works.

* Extra words added to commands that do not take parameters, such as `help`, `list`, `exit`, and `clear`, will be ignored.  
  For example, `help 123` will still be read as `help`.

* If you are using a PDF version of this guide, be careful when copying commands that span multiple lines, since some spaces around line breaks may be lost when pasting.
</div>

### Viewing help : `help`

Opens the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an athlete : `addathlete`

Adds a new athlete to Pacebook.

Format: `addathlete n/NAME a/AGE p/PHONE_NUMBER e/EMAIL ad/ADDRESS d/START_DATE [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An athlete can have any number of tags (including 0)
</div>

Examples:
* `addathlete n/John Doe a/21 p/98765432 e/johnd@example.com ad/John street, block 123, #01-01 d/01/01/2001`
* `addathlete n/Betsy Crowe a/19 t/friend e/betsycrowe@example.com ad/Newgate Street p/1234567 d/02/02/2002 t/professional`

### Listing all athletes : `list`

Lists all athletes currently stored in Pacebook.

Format: `list`

### Editing an athlete : `edit`

Updates an existing athlete's details.

Format: `edit INDEX [n/NAME] [a/AGE] [p/PHONE] [e/EMAIL] [ad/ADDRESS] [d/START_DATE] [t/TAG]…​`

* Edits the athlete at the specified `INDEX`. 
* The index refers to the index number shown in the displayed athlete list. The index **must be a positive integer** 1, 2, 3, …​
* You must provide at least one field to edit.
* Any field you include will replace the athlete’s current value for that field.
* If you edit tags, the old tags will be replaced completely.
* You can remove all the athlete’s tags by typing `t/` without
    entering any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st athlete to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd athlete to be `Betsy Crower` and clears all existing tags.

### Finding athletes by name : `find`

Finds athletes whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only names are searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Athletes matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting an athlete : `deleteathlete`

Deletes an athlete from Pacebook.

Format: `deleteathlete INDEX`

* Deletes the athlete at the specified `INDEX`.
* The index refers to the index number shown in the displayed athlete list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `deleteathlete 2` deletes the 2nd athlete in Pacebook.
* `find Betsy` followed by `deleteathlete 1` deletes the 1st athlete in the results of the `find` command.

### Viewing an athlete profile and training records : `viewathlete`

Displays an athlete’s profile and their training records.

Format: `viewathlete INDEX`

* Displays the athlete at the specified `INDEX`.
* The index refers to the index number shown in the displayed athlete list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `list` followed by `viewathlete 2` displays the 2nd athlete in Pacebook.
* `find Betsy` followed by `viewathlete 1` displays the 1st athlete in the results of the `find` command.

### Adding a timing record : `addtiming`

Adds a timing record for an athlete at a specific running distance.

Format: `addtiming INDEX dist/DISTANCE min/MINUTES sec/SECONDS`

* Adds a timing record to the athlete at the specified `INDEX`. 
* The index refers to the index number shown in the displayed athlete list. 
* `INDEX` must be a positive integer 1, 2, 3, … 
* Pacebook records distance in kilometres, using up to 2 decimal places.

Examples:
* `list` followed by `addtiming 2 dist/2.4 min/10 sec/30` adds a training record to the 2nd athlete in Pacebook.
* `find Betsy` followed by `addtiming 1 dist/0.2 min/0 sec/30` adds a training record to the 1st athlete in the results of the `find` command.

### Deleting a timing record : `deletetiming`

Deletes a timing record from an athlete’s profile.

Format: `deletetiming ATHLETE_INDEX RECORD_INDEX`

* Deletes the timing record at `RECORD_INDEX` for the athlete at `ATHLETE_INDEX`.
* The `ATHLETE_INDEX` refers to the index number shown in the displayed athlete list.
* The `RECORD_INDEX` refers to the index number shown in the displayed training records.
* Both `ATHLETE_INDEX` and `RECORD_INDEX` must be positive integers 1, 2, 3, …

Examples:
* `list`, followed by `viewathlete 2` and `deletetiming 2 2` deletes the 2nd training record from the 2nd athlete in Pacebook.
* `find Betsy`, followed by `viewathlete 1` and `deletetiming 1 2` deletes the 2nd training record from the 1st athlete in the results of the `find` command.

### Clearing all entries : `clear`

Clears all athletes from Pacebook.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Pacebook saves your data automatically whenever a command changes it, so you do not need to save manually.

### Editing the data file

Pacebook stores its data as a JSON file at `[JAR file location]/data/addressbook.json`. Advanced users can edit this file directly if needed.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes corrupt the data file, Pacebook will discard all data and start with an empty data file the next time it runs. Hence, it is recommended to store a backup of the file before editing it.<br>
Furthermore, certain edits can cause the Pacebook to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Pacebook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the application window will open off-screen. The solution is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**AddAthlete** | `addathlete n/NAME a/AGE p/PHONE_NUMBER e/EMAIL ad/ADDRESS d/START_DATE [t/TAG]…​` <br> e.g., `addathlete n/James Ho a/30 p/82224444 e/jamesho@example.com ad/123, Clementi Rd, 1234665 d/03/03/2003 t/friend t/colleague`
**AddTiming** | `addtiming INDEX dist/DISTANCE min/MINUTES sec/SECONDS`<br> e.g., `addtiming 2 dist/2.4 min/10 sec/30`
**Clear** | `clear`
**DeleteAthlete** | `deleteathlete INDEX`<br> e.g., `deleteathlete 3`
**DeleteTiming** | `deletetiming ATHLETE_INDEX RECORD_INDEX`<br> e.g., `deletetiming 2 2`
**Edit** | `edit INDEX [n/NAME] [a/AGE] [p/PHONE_NUMBER] [e/EMAIL] [ad/ADDRESS] [d/START_DATE] [t/TAG]…​`<br> e.g., `edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**ViewAthlete** | `viewathlete INDEX`<br> e.g., `viewathlete 3`
