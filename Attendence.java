/*
 * Attendence.java
 * 
 * Copyright 2023 hamoncal25 <hamoncal25@US-CS-LAB-08>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */


public class Attendence {
	
	private String fName;
    private String lName;
    private int attNum;
    private int comp;

    public Attendence(int RAttNum, String RLName, String RFName, int RComp)
    {
        RAttNum = attNum;
        RLName = lNama;
        RFName = fName;
        RComp = comp;
    }
    
    public String toString()
    {
        return fName + " " + lName + " from company " + comp + ": number";
    }
    
}

