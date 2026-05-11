# automate-mutual-funds

**Project**
- **Description:** Small Java project that scrapes stock prices from moneycontrol.com using Selenium and writes results to an Excel file using Apache POI.

**Files**
- **Main classes:** [src/main/java/automateMF.java](src/main/java/automateMF.java) and [src/main/java/automate_mutualfunds.java](src/main/java/automate_mutualfunds.java)
- **Build:** [pom.xml](pom.xml)
- **Output:** Stock workbook `StockDetails.xlsx` (created in project root)

**Prerequisites**
- **Java:** JDK 17 or higher.
- **Maven:** `mvn` on PATH.
- **Browser:** Microsoft Edge or Google Chrome installed.
- **WebDriver:** matching browser driver (msedgedriver/chromedriver) and correct path set in the source or provided as a system property.

**Quick Run (recommended)**
- From project root, run the class you want with Maven exec. Example (PowerShell/CMD):

  - `mvn "-Dexec.mainClass=automateMF" exec:java`
  - or to run the other main class: `mvn "-Dexec.mainClass=automate_mutualfunds" exec:java`

**Two-step build + run**
- `mvn clean package`
- `mvn exec:java -Dexec.mainClass=automateMF`

**Driver configuration**
- The code currently sets the Edge driver path inside the classes with `System.setProperty("webdriver.edge.driver", "C:\\path\\to\\msedgedriver.exe")`.
- Options:
  - Edit the Java file(s) and set the correct absolute path to `msedgedriver.exe` or `chromedriver.exe`.
  - Or pass a JVM property when running (example PowerShell quoting may be required):

    `mvn "-Dexec.mainClass=automateMF" "-Dwebdriver.edge.driver=C:\\path\\to\\msedgedriver.exe" exec:java`

**Auto-manage driver (recommended)**
- Add WebDriverManager and replace manual `System.setProperty(...)` with:

  ```java
  WebDriverManager.edgedriver().setup();
  WebDriver driver = new EdgeDriver();
  ```

- Dependency to add to `pom.xml`:

  ```xml
  <dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.4.0</version>
  </dependency>
  ```

**Notes & troubleshooting**
- You may see SLF4J/log4j warnings — they are harmless for running but can be silenced by adding a logging implementation (optional).
- If Selenium warns about CDP version mismatch, the browser and selenium/devtools dependency may be out of sync; using WebDriverManager or updating Selenium/devtools resolves most issues.
- The program launches a real browser; make sure interactive sessions are allowed on your machine when running.

If you want, I can add WebDriverManager and update the code to run headless and suppress those warnings — tell me which option you prefer.
