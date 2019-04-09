package application.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentTable {

  private StringProperty tblEnrollNo;
  private StringProperty tblName;
  private StringProperty tblAge;
  private StringProperty tblGender;
  private StringProperty tblAadhaarNo;
  private StringProperty tblCaste;
  private StringProperty tblCategory;
  private StringProperty tblClass;
  private StringProperty tblYear;




  public StudentTable(String tblEnrollNo, String tblName, String tblAge, String tblGender, String tblAadhaarNo, String tblCaste, String tblCategory, String tblClass, String tblYear) {

    this.tblEnrollNo = new SimpleStringProperty(tblEnrollNo);
    this.tblName = new SimpleStringProperty(tblName);
    this.tblAge = new SimpleStringProperty(tblAge);
    this.tblGender = new SimpleStringProperty(tblGender);
    this.tblAadhaarNo = new SimpleStringProperty(tblAadhaarNo);
    this.tblCaste = new SimpleStringProperty(tblCaste);
    this.tblCategory = new SimpleStringProperty(tblCategory);
    this.tblClass = new SimpleStringProperty(tblClass);
    this.tblYear = new SimpleStringProperty(tblYear);



  }

  public String getTblEnrollNo() {
    return tblEnrollNo.get();
  }

  public void setTblEnrollNo(String tblEnrollNo) {
    this.tblEnrollNo.set(tblEnrollNo);
  }

  public StringProperty tblEnrollNoProperty() {
    return tblEnrollNo;
  }

  public String getTblName() {
    return tblName.get();
  }

  public void setTblName(String tblName) {
    this.tblName.set(tblName);
  }

  public StringProperty tblNameProperty() {
    return tblName;
  }

  public String getTblAge() {
    return tblAge.get();
  }

  public void setTblAge(String tblAge) {
    this.tblAge.set(tblAge);
  }

  public StringProperty tblAgeProperty() {
    return tblAge;
  }

  public String getTblGender() {
    return tblGender.get();
  }

  public void setTblGender(String tblGender) {
    this.tblGender.set(tblGender);
  }

  public StringProperty tblGenderProperty() {
    return tblGender;
  }

  public String getTblCaste() {
    return tblCaste.get();
  }

  public void setTblCaste(String tblCaste) {
    this.tblCaste.set(tblCaste);
  }

  public StringProperty tblCasteProperty() {
    return tblCaste;
  }

  public String getTblCategory() {
    return tblCategory.get();
  }

  public void setTblCategory(String tblCategory) {
    this.tblCategory.set(tblCategory);
  }

  public StringProperty tblCategoryProperty() {
    return tblCategory;
  }

  public String getTblAadhaarNo() {
    return tblAadhaarNo.get();
  }

  public void setTblAadhaarNo(String tblAadhaarNo) {
    this.tblAadhaarNo.set(tblAadhaarNo);
  }

  public StringProperty tblAadhaarNoProperty() {
    return tblAadhaarNo;
  }

  public String getTblClass() {
    return tblClass.get();
  }

  public StringProperty tblClassProperty() {
    return tblClass;
  }

  public void setTblClass(String tblClass) {
    this.tblClass.set(tblClass);
  }

  public String getTblYear() {
    return tblYear.get();
  }

  public StringProperty tblYearProperty() {
    return tblYear;
  }

  public void setTblYear(String tblYear) {
    this.tblYear.set(tblYear);
  }
}
