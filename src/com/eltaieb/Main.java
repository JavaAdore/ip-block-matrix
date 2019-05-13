package com.eltaieb;

import java.util.Scanner;

public class Main {
	
	private static final byte BLOCKED = 1;
	
	public static void main(String[] args) {

		Main main = new Main();
		main.blockIps();
		System.out.println();
	}
	
	// this is the data structure 
	byte[][][][] arr = new byte[192][][][];
	private void blockIp(int firstLevel, int secondLevel, int thirdLevel, int forthLevel) {
		// by this way I mark ip with only static byte BLOCKED
		if (arr[firstLevel] == null) {
			arr[firstLevel] = new byte[256][][];
		}
		if (arr[firstLevel][secondLevel] == null) {
			arr[firstLevel][secondLevel] = new byte[256][];
		}
		if (arr[firstLevel][secondLevel][thirdLevel] == null) {
			arr[firstLevel][secondLevel][thirdLevel] = new byte[256];
		}
		arr[firstLevel][secondLevel][thirdLevel][forthLevel] = BLOCKED;
	}
	// assuming passed IP is valid and in format x.x.x.x
	private boolean isBlocked(String string) {
		int[] indecies  = toIntArray(string);
		int firstLevel  = indecies[0];
		int secondLevel = indecies[1];
		int thirdLevel  = indecies[2];
		int forthLevel  = indecies[3];
		return isBlocked(firstLevel, secondLevel, thirdLevel, forthLevel);
	}
	private boolean isBlocked(int a, int i, int j, int k) {
		// check if the IP is blocked or not in o(1)
		if (arr[a] != null) {
			if (arr[a][i] != null) {
				if (arr[a][i][j] != null) {
					return arr[a][i][j][k] == BLOCKED;
				}
			}
		}
		return false;
	}

	private int[] toIntArray(String string) {
		int[] partsAsIntegers = new int[4];
		String[] parts = string.split("\\.");
		for (int i = 0; i < 4; i++) {
			partsAsIntegers[i] = Integer.parseInt(parts[i]);
		}
		return partsAsIntegers;
	}
	
	
	
	public void blockIp(String ip)
	{
		int[] indecies  = toIntArray(ip);
		int firstLevel  = indecies[0];
		int secondLevel = indecies[1];
		int thirdLevel  = indecies[2];
		int forthLevel  = indecies[3];
		blockIp(firstLevel, secondLevel, thirdLevel, forthLevel);

	}

	private void blockIps() {
		int counter = 1;
		for (int a = 10; a < 13; a++) {
			for (int i = 1; i < 255; i++) {
				for (int j = 1; j < 255; j++) {
					for (int k = 1; k < 255; k++) {

						blockIp(a, i, j, k);
						counter++;
					}
				}
			}
		}
		System.out.println("number of blocked ips are " + counter);
		System.out.println(isBlocked("10.33.3.86"));
		// just not to end the program
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

	}
}
