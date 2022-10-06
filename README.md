# JsonTreeToCSV
## Getting started
* Use command git clone https://github.com/MaksymHryhorov/JsonTreeToCSV.git
* Add your pdf file to the resource folder.
* Open JsonReader class in reader package and change file name
```java
File file = new File("D:\\Projects\\JsonTree\\src\\main\\resources\\file.json");

return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
```

Comment: "file.json" You must write your own pdf file

* Let's go to the Main class and start the program. In resource package you will see csv file with result
