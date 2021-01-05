Feature: 01_Sap 
  
Scenario Outline: Login and close
Given Login in to SAP System '<testCaseID>'
Given Select Transaction Code '<tcode>'
Given Close SAP System

Examples: 
| tcode |testCaseID| 
| VA01  |Sap_001   |