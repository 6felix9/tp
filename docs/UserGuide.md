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
  For example, `n/NAME [t/TAG]` can be used as `n/John Doe t/sprinter` or just `n/John Doe`.

* Items followed by `…` can be used multiple times, including zero times.  
  For example, `[t/TAG]…` can be omitted completely, or used as `t/teamA`, `t/teamA t/relay`, and so on.

* Parameters can be entered in any order unless otherwise stated.  
  For example, if the command format is `n/NAME p/PHONE_NUMBER`, then `p/PHONE_NUMBER n/NAME` also works.

* Extra words added to commands that do not take parameters, such as `help`, `list`, `exit`, and `clear`, will be ignored.  
  For example, `help 123` will still be read as `help`.

* If you are using a PDF version of this guide, be careful when copying commands that span multiple lines, since some spaces around line breaks may be lost when pasting.
</div>

### Viewing help : `help`

Opens the help window.

Format: `help`

Expected output:
* A help window opens with a summary of available commands.

Tip:
* Use `help` whenever you forget a command format instead of guessing the prefixes.

Customized example:
* Before adding a new athlete for the first time, type `help` to quickly confirm the required prefixes.

---

### Adding an athlete : `addathlete`

Adds a new athlete to Pacebook.

Format: `addathlete n/NAME a/AGE p/PHONE_NUMBER e/EMAIL ad/ADDRESS d/START_DATE [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**  
An athlete can have any number of tags, including none.
</div>

Warnings:
* All compulsory fields must be provided.
* `AGE` must be valid according to the app’s accepted format.
* `PHONE_NUMBER` and `EMAIL` must be valid.
* Duplicate athletes may be rejected if they match an existing athlete’s identity.
* Avoid using vague names such as `John` if you coach multiple athletes with similar names.

Expected output:
* A success message confirming that the athlete has been added.
* The athlete appears in the athlete list.

Examples:
* `addathlete n/John Doe a/21 p/98765432 e/johnd@example.com ad/John street, block 123, #01-01 d/01/01/2001`
* `addathlete n/Betsy Crowe a/19 t/friend e/betsycrowe@example.com ad/Newgate Street p/1234567 d/02/02/2002 t/professional`

Customized example:
* To add a new sprinter from your club before a new training block:
    * `addathlete n/Alex Tan a/20 p/91234567 e/alex@example.com ad/10 Jurong West Ave 5 d/15/03/2024 t/sprinter t/teamA`

---

### Listing all athletes : `list`

Lists all athletes currently stored in Pacebook.

Format: `list`

Expected output:
* All athletes currently stored in Pacebook are displayed.

Tip:
* Use `list` before commands like `viewathlete`, `edit`, and `deleteathlete` if you want to confirm the current athlete indices.

Customized example:
* Before planning a weekly track session, type `list` to view all available athletes in one place.

---

### Editing an athlete : `edit`

Updates an existing athlete's details.

Format: `edit INDEX [n/NAME] [a/AGE] [p/PHONE] [e/EMAIL] [ad/ADDRESS] [d/START_DATE] [t/TAG]…​`

* Edits the athlete at the specified `INDEX`.
* The index refers to the index number shown in the displayed athlete list.
* The index **must be a positive integer**: `1, 2, 3, …`
* You must provide at least one field to edit.
* Any field you include will replace the athlete’s current value for that field.
* If you edit tags, the old tags will be replaced completely.
* You can remove all of the athlete’s tags by typing `t/` without entering anything after it.

Warnings:
* Make sure you are editing the correct athlete index, especially after using `find`.
* Editing tags replaces all existing tags, not just one of them.

Expected output:
* A success message showing the updated athlete details.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com`
* `edit 2 n/Betsy Crower t/`

Customized example:
* If an athlete changes phone number before race season:
    * `edit 3 p/97654321`

---

### Finding athletes by name : `find`

Finds athletes whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only names are searched.
* Only full words will be matched. e.g. `Han` will not match `Hans`
* Athletes matching at least one keyword will be returned (i.e. `OR` search).  
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Warnings:
* `find` does not search phone number, address, tags, or timing records.
* If no athletes match, the list will be empty.

Expected output:
* Only athletes matching the given keywords are shown.

Examples:
* `find John`
* `find alex david`

Customized example:
* If you coach many athletes and want to pull up all athletes named Alex before an interval session:
    * `find Alex`

---

### Deleting an athlete : `deleteathlete`

Deletes an athlete from Pacebook.

Format: `deleteathlete INDEX`

* Deletes the athlete at the specified `INDEX`.
* The index refers to the index number shown in the displayed athlete list.
* The index **must be a positive integer**: `1, 2, 3, …`

Warnings:
* This action removes the athlete and their associated training data from the app.
* Double-check the athlete index before deleting.

Expected output:
* A success message confirming which athlete was deleted.

Examples:
* `list` followed by `deleteathlete 2`
* `find Betsy` followed by `deleteathlete 1`

Customized example:
* If a runner leaves the club and you no longer need their profile:
    * `deleteathlete 4`

---

### Viewing an athlete profile and training records : `viewathlete`

Displays an athlete’s profile and their training records.

Format: `viewathlete INDEX`

* Displays the athlete at the specified `INDEX`.
* The index refers to the index number shown in the displayed athlete list.
* The index must be a positive integer: `1, 2, 3, …`

Expected output:
* The selected athlete’s profile is shown.
* Any stored run timings or training records for that athlete are shown below the profile details.

Tip:
* Use `viewathlete` after `find` to quickly inspect one athlete without scrolling through the full list.

Examples:
* `list` followed by `viewathlete 2`
* `find Betsy` followed by `viewathlete 1`

Customized example:
* Before deciding an athlete’s race pace target, use:
    * `viewathlete 2`
      to review their details and recent timing history.

---

### Adding a timing record : `addtiming`

Adds a 2.4km timing record to an athlete.

Format: `addtiming INDEX min/MINUTES sec/SECONDS`

* Adds a timing record to the athlete at the specified `INDEX`.
* The index refers to the index number shown in the displayed athlete list.
* `INDEX` must be a positive integer: `1, 2, 3, …`
* The running distance is fixed at **2.4km**.
* `MINUTES` must be a non-negative integer.
* `SECONDS` must be between `0` and `59.99`.
* The total timing must be greater than `0`.

Warnings:
* This command records only **2.4km timings**.
* If you enter `min/0 sec/0`, the command will be rejected.
* If the same athlete has multiple timing records, make sure you add the new record to the correct athlete profile.

Expected output:
* A success message confirming that the timing was added.
* If the new timing is the athlete’s fastest 2.4km timing so far, a **new personal best** message is also shown.

Examples:
* `list` followed by `addtiming 2 min/10 sec/30`
* `find Betsy` followed by `addtiming 1 min/11 sec/45`

Customized example:
* After timing an athlete during a fitness test:
    * `addtiming 3 min/9 sec/58`
      This records a 2.4km timing of 9 min 58 s for athlete 3.

---

### Deleting a timing record : `deletetiming`

Deletes a timing record from an athlete’s profile.

Format: `deletetiming ATHLETE_INDEX RECORD_INDEX`

* Deletes the timing record at `RECORD_INDEX` for the athlete at `ATHLETE_INDEX`.
* `ATHLETE_INDEX` refers to the index number shown in the displayed athlete list.
* `RECORD_INDEX` refers to the index number shown in the displayed training records.
* Both `ATHLETE_INDEX` and `RECORD_INDEX` must be positive integers: `1, 2, 3, …`

Warnings:
* Make sure you check the athlete profile with `viewathlete` first so that you delete the correct timing record.
* Deleting a timing record may affect the athlete’s visible performance history.

Expected output:
* A success message confirming that the selected timing record was deleted.

Examples:
* `list`, followed by `viewathlete 2` and `deletetiming 2 2`
* `find Betsy`, followed by `viewathlete 1` and `deletetiming 1 2`

Customized example:
* If a timing was entered incorrectly after a training session:
    * `deletetiming 3 1`

---

### Clearing all entries : `clear`

Clears all athletes from Pacebook.

Format: `clear`

Warnings:
* This removes all athletes currently stored in the app.
* Use this only if you are sure you want to wipe the current dataset.

Expected output:
* All athletes are removed from the list.

Tip:
* Avoid using `clear` if you only want to remove one athlete. Use `deleteathlete` instead.

---

### Exiting the program : `exit`

Exits the program.

Format: `exit`

Expected output:
* The application closes.

Tip:
* There is no need to save manually before exiting because Pacebook saves automatically after successful changes.

---

### Saving the data

Pacebook saves your data automatically whenever a command changes it, so you do not need to save manually.

Expected output:
* No separate save confirmation may be shown, but your data will be written to the data file after successful commands.

Tip:
* Close the application normally using `exit` to reduce the risk of interrupted file writes.

---

### Editing the data file

Pacebook stores its data as a JSON file at `[JAR file location]/data/addressbook.json`. Advanced users can edit this file directly if needed.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**  
If your changes corrupt the data file, Pacebook may discard unreadable data or fail to load it correctly the next time it runs. It is strongly recommended to back up the file before editing it manually.  
Furthermore, certain edits can cause Pacebook to behave in unexpected ways if values are outside the accepted format. Edit the data file only if you are confident that you can update it correctly.
</div>

Tip:
* Manual file editing is meant for advanced users only. Most users should use the app commands instead.

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
**AddTiming** | `addtiming INDEX min/MINUTES sec/SECONDS`<br> e.g., `addtiming 2 min/10 sec/30`
**Clear** | `clear`
**DeleteAthlete** | `deleteathlete INDEX`<br> e.g., `deleteathlete 3`
**DeleteTiming** | `deletetiming ATHLETE_INDEX RECORD_INDEX`<br> e.g., `deletetiming 2 2`
**Edit** | `edit INDEX [n/NAME] [a/AGE] [p/PHONE_NUMBER] [e/EMAIL] [ad/ADDRESS] [d/START_DATE] [t/TAG]…​`<br> e.g., `edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**ViewAthlete** | `viewathlete INDEX`<br> e.g., `viewathlete 3`