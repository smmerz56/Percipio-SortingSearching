package com.skillsoft.datastructures;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) {
//		int unsortedList[] = new int[] {40, 50, 60, 20, 30, 70, 100, 80, 10, 90};
//		int unsortedList[] = new int[] {10,20,40,30,50,70,60, 80, 90, 100};
//		System.out.println(Arrays.toString(unsortedList));
//		shellSort(unsortedList);
		
		String unsortedList[] = new String[] {
				"Fiona", "Dora", "Alex", "Jeff",
				"Elise", "Irene", "Gerald", "Ben",
				"Harry", "Carl"};
//		System.out.println(Arrays.toString(unsortedList));
////		mergeSort(unsortedList);
//		quickSort(unsortedList, 0, unsortedList.length-1);
//		System.out.println("\n"+Arrays.toString(unsortedList));
		
		String sortedList[] = new String[] {"Alex", "Ben", "Carl", "Dora", "Elise", 
											"Fiona", "Gerald", "Harry", "Irene", "Jeff", 
											"Kris", "Lewis", "Mary", "Nora", "Ophelia", "Peter"};
//		System.out.println("\nElement index: "+ binarySearch(sortedList, "Harry"));
//		System.out.println("\nElement index: "+ binarySearch(sortedList, "Jeff"));
//		
//		System.out.println("\nElement index: "+ binarySearchRecursion(sortedList, "Harry", 0, sortedList.length -1));
//		System.out.println("\nElement index: "+ binarySearchRecursion(sortedList, "Jeff",0, sortedList.length -1));
		
		int jumpLength = (int) Math.sqrt(sortedList.length);
		System.out.println("\nElement index: "+ jumpSearch(sortedList, "Gerald",jumpLength));
		System.out.println("\nElement index: "+ jumpSearch(sortedList, "Mary",jumpLength));
		System.out.println("\nElement index: "+ jumpSearch(sortedList, "Ophelia",jumpLength));
	}
	//------------------------------------------------------------------------------
	//Improvement over binary search, works well with evenly distributed elements
	public static int interpolationSearch(int[] list, int element) {
		System.out.println("\nSearching..."+ element + ": ");
		
		int low = 0;
		int high = list.length -1;
		
		while(low <= high) {
			int mid = low + ((element - list[low]) * (high - low)) / (list[high] - list[low]);
			
			System.out.println("Low: "+ low + " High: "+ high +
					" Mid: "+mid+" Mid element: "+ list[mid]);
			
			if(list[mid] == element) {
				return mid;
			}
			else if (list[mid]< element) {
				low = mid + 1;
			}else {
				high = mid -1;
			}
		}
		return -1;
	}
	//------------------------------------------------------------------------------
	//Improvement over linear search
	public static int jumpSearch(String[] list, String element, int jumpLength) {
		System.out.println("\nSearching..." + element + ": ");
		
		int i = 0;
		
		while(list[i].compareTo(element) <= 0) {
			System.out.println("Element is greater than or equal to: "+ list[i]);
			
			i = i + jumpLength;
			
			if(i >= list.length) {
				break;
			}
		}
		
		int startIndex = i - jumpLength;
		int endIndex = Math.min(i,  list.length);
		
		System.out.println("Looking between: "+ startIndex + " and: "+ endIndex);
		
		for(int j = startIndex; j < endIndex; j++) {
			if(list[j].equals(element)) {
				return j;
			}
		}
		return -1;
	}
	//------------------------------------------------------------------------------
	public static int binarySearchRecursion(String[] list, String element, int low, int high) {
		
		if(low > high) {
			return -1;
		}
		System.out.println("\nSearching... "+ element+ ": ");

		int mid = (low + high) / 2;
		
		System.out.println("Low: "+ low + " High: "+ high+ " Mid: "+mid+ " Mid element: "+list[mid]);
		
		if(list[mid].equals(element)) {
			return mid;
		}
		else if(list[mid].compareTo(element) < 0) {//element at mid is less than the element you are searching for
			return binarySearchRecursion(list, element, mid +1, high);
		} else {//element at mid is greater than the element you are searching for
			return binarySearchRecursion(list, element, low, mid -1);
		}
	}
	//------------------------------------------------------------------------------
	public static int binarySearch(String[] list, String element) {
		System.out.println("\nSearching... "+ element+ ": ");
		
		int low = 0;
		int high = list.length-1;
		
		while(low <= high) {
			int mid = (low + high) / 2;
			
			System.out.println("Low: "+ low + " High: "+ high+ " Mid: "+mid+ " Mid element: "+list[mid]);
			
			if(list[mid].equals(element)) {
				return mid;
			}
			else if(list[mid].compareTo(element) < 0) {//element at mid is less than the element you are searching for
				low = mid +1;
			} else {//element at mid is greater than the element you are searching for
				high = mid -1;
			}
		}
		return -1;
	}
	//------------------------------------------------------------------------------
	//Divide and conquer better for large lists
	public static void quickSort(String[] listToSort, int low, int high) {
		if(low >= high) {
			return;
		}
		int pivotIndex = partition(listToSort, low, high);
		
		quickSort(listToSort, low, pivotIndex -1);
		quickSort(listToSort, pivotIndex+1, high);
	}
	
	//------------------------------------------------------------------------------
	public static int partition(String[] listToSort, int low, int high) {
		String pivot = listToSort[low];
		
		int l = low;
		int h = high;
		
		System.out.println("\nPivot = "+ pivot);
		
		while(l < h) {
			while(listToSort[l].compareTo(pivot) <= 0 && l < h) {
				l++;
			}
			while(listToSort[h].compareTo(pivot) > 0) {
				h--;
			}
			if(l<h) {
				swap(listToSort, l, h);
				
				System.out.print("Swapping: " + l + " and " + h + " ");
				System.out.println(Arrays.toString(listToSort));
			}
		}
		if (low != h) {
			swap(listToSort, low, h);
			
			System.out.print("Swapping: " + low + " and "+ h + " ");
			System.out.println(Arrays.toString(listToSort));
		}
		System.out.println("Partitioned: "+Arrays.toString(listToSort)+ " around pivot: "+pivot);
		return h;
	}
	//------------------------------------------------------------------------------
	//Divide and conquer better for large lists
	//Needs temp space
	public static void mergeSort(String[] listToSort) {
		if(listToSort.length == 1) {
			return;
		}
		
		int midIndex = listToSort.length / 2 + listToSort.length % 2;
		
		String[] listFirstHalf = new String[midIndex];
		String[] listSecondHalf = new String[listToSort.length - midIndex];
		
		split(listToSort, listFirstHalf, listSecondHalf);
		
		System.out.println("\n Split: " +Arrays.toString(listFirstHalf) + "  " + Arrays.toString(listSecondHalf));
		
		mergeSort(listFirstHalf);
		mergeSort(listSecondHalf);
		
		merge(listToSort, listFirstHalf, listSecondHalf);
		System.out.println("\nMerged: " + Arrays.toString(listToSort));
	}
	//------------------------------------------------------------------------------
	public static void merge(String[] listToSort, String[] listFirstHalf,String[] listSecondHalf) {
		int mergeIndex = 0;
		
		int firstHalfIndex = 0;
		int secondHalfIndex = 0;
		
		while(firstHalfIndex < listFirstHalf.length &&
				secondHalfIndex < listSecondHalf.length) {
			
			if (listFirstHalf[firstHalfIndex].compareTo(listSecondHalf[secondHalfIndex]) < 0) {
				listToSort[mergeIndex] = listFirstHalf[firstHalfIndex];
				firstHalfIndex++;
			} else if (secondHalfIndex < listSecondHalf.length) {
				listToSort[mergeIndex] = listSecondHalf[secondHalfIndex];
				secondHalfIndex++;
			}
			mergeIndex++;
		}
		if(firstHalfIndex < listFirstHalf.length) {
			while(mergeIndex <listToSort.length) {
				listToSort[mergeIndex++] = listFirstHalf[firstHalfIndex++];
			}
		}
		if(secondHalfIndex < listSecondHalf.length) {
			while(mergeIndex <listToSort.length) {
				listToSort[mergeIndex++] = listSecondHalf[secondHalfIndex++];
			}
		}
	}
	//------------------------------------------------------------------------------
	public static void split(String[] listToSort, String[] listFirstHalf,String[] listSecondHalf) {
		int secondHalfStartIndex = listFirstHalf.length;
		
		for(int index = 0; index < listToSort.length; index++) {
			if(index < secondHalfStartIndex) {
				listFirstHalf[index] = listToSort[index];
			} else {
				listSecondHalf[index - secondHalfStartIndex] = listToSort[index];
			}
		}
	}
	//------------------------------------------------------------------------------
	//Good for small
	public static void shellSort(int[] listToSort) {
		int increment = listToSort.length / 2;
//		int k = 1;
//		int increment = (3 * k -1) /2;Knuths Interval
		while(increment >= 1) {
			insertionSortForShell(listToSort, increment);
			increment = increment / 2;
//			k++;
//			increment = (3 * k -1) /2;
		}
	}
	
	//------------------------------------------------------------------------------
	public static void insertionSortForShell(int[] listToSort, int increment) {
		for(int i =0; i + increment < listToSort.length; i = i + 1) {
			
			System.out.println("\ni= "+ i + " increment = " + increment);
			
			for(int j = i + increment; j - increment >= 0; j= j - increment) {
				
				if(listToSort[j] < listToSort[j-increment]){
					
					swap(listToSort, j, j- increment);
					
					System.out.print("Swapping: "+ j+" and "+(j-increment)+" ");
					System.out.println(Arrays.toString(listToSort));
				}
				else {
					break;
				}
			}

		}
	}
	//------------------------------------------------------------------------------
	//Good for small
	public static void insertionSort(int[] listToSort) {
		for(int i =0; i < listToSort.length -1; i++) {
			
			System.out.println("\ni= "+ i);
			
			for(int j = i + 1; j > 0; j--) {
				
				if(listToSort[j] < listToSort[j-1]){
					
					swap(listToSort, j, j-1);
					
					System.out.print("Swapping: "+ j+" and "+(j-1)+" ");
					System.out.println(Arrays.toString(listToSort));
				}
				else {
					break;
				}
			}

		}
	}
	//------------------------------------------------------------------------------
	//Good for small
	public static void bubbleSort(int[] listToSort) {
		for(int i =listToSort.length-1; i > 0; i--) {
			
			boolean swapped = false;
			
			System.out.println("\ni= "+ i);
			
			for(int j = 0; j < i; j++) {
				
				if(listToSort[j] > listToSort[j+1]){
					
					swap(listToSort, j, j+1);
					
					swapped = true;
					
					System.out.print("Swapping: "+ j+" and "+(j+1)+" ");
					System.out.println(Arrays.toString(listToSort));
				}
			}
			if(!swapped) {
				break;
			}
		}
	}
	//------------------------------------------------------------------------------
	//O(N^2)
	public static void selectionSort(int[] listToSort) {
		
		for(int i =0; i < listToSort.length; i++) {
			
			System.out.println("\ni= "+ i);
			
			for(int j = i + 1; j < listToSort.length; j++) {
				
				if(listToSort[i] > listToSort[j]){
					
					swap(listToSort, i, j);
					
					System.out.println("Swapping: "+ i+" and "+j+" ");
					System.out.println(Arrays.toString(listToSort));
				}
			}
		}
	}
	//------------------------------------------------------------------------------
	public static void swap(int[] list, int iIndex, int jIndex) {
		int temp = list[iIndex];
		
		list[iIndex] = list[jIndex];
		list[jIndex] = temp;
	}
	//------------------------------------------------------------------------------
	public static void swap(String[] list, int iIndex, int jIndex) {
		String temp = list[iIndex];
		
		list[iIndex] = list[jIndex];
		list[jIndex] = temp;
	}
}
