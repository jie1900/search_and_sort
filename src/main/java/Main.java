import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String column1Format = "%-30s";  // left 30
    static String column2Format = "%-15s";  // lft 15
    static String column3Format = "%6s";   // right 6
    static String formatInfo = column2Format ;

    static int[] array_for_3rd_sorting = {-7,1,-81,-56,-91,37,41,28,34,-27};
    static int[] array_for_4th_sorting = {100,400,300,200,700,900,800,600,500};

    static List<String> arrList = new ArrayList<>();//
    static List<Integer> countList = new ArrayList<>();
    static List<Long> timesList = new ArrayList<>();
    static Long start = System.nanoTime();

    public static void main(String[] args) {
        sc();
    }

    static void sc(){
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("SORT MENU");
        System.out.println("1 Linear Searching");
        System.out.println("2 Binary  Searching");
        System.out.println("3 O(n^2) Type of sorting");
        System.out.println("4 O(n*log(n)) Type of sorting");
        System.out.println("5 Sorting Performance");
        System.out.println("");
        System.out.println("q/Q Quit");
        System.out.println("Your choice:");
        String choose = sc.nextLine();
        if(choose.equals("q") || choose.equals("Q")){
            System.exit(0);
        }
        else if(Integer.parseInt(choose) == 1){
            System.out.println("In the list are values 0, ..., 9; which value would you like to search using linear searching?");
            int choose1 = sc.nextInt();
            int Linear_index = linearSearch(array_for_4th_sorting, choose1);
            print1(choose1, Linear_index);
            sc();
        }
        else if(Integer.parseInt(choose) == 2){
            System.out.println("In the list are values 1,3,5,...,9; which value would you like to search using binary searching?");
            int choose2 = sc.nextInt();
            int[] arr = array_for_4th_sorting;
            int position = BinarySearch(arr, choose2);
            if(position == -1){
                System.out.println("Number "+choose2+" is not found in the array!");
            }else{
                System.out.println("Number "+choose2+" found!And the index of the number is : "+(position+1));
            }
            sc();
        }
        else if(Integer.parseInt(choose)==3){
            new Main().choose3(1);
            sc();
        }
        else if(Integer.parseInt(choose) == 4){
            new Main().choose4(1);
            sc();
        }
        else if(Integer.parseInt(choose) == 5){
            new Main().choose5();
            sc();
        }
        else{
            System.out.println("Not Done");
            sc();
        }
    }

    //1.Linear Searching
    public static int linearSearch(int arr[], int targetParameter ) {
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == targetParameter)
                return index;
        }
        return -1;
    }

    public static void print1(int targetParameter, int index) {
        if (index == -1){
            System.out.println("Number "+targetParameter + " is not found in the array!");
        }
        else {
            System.out.println("Number "+targetParameter + " found!And the index of the number is : " + (index+1));
        }
    }

    //2.Linear Searching
    public static int BinarySearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;

        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }
        while(low <= high){
            middle = (low + high) / 2;
            if(arr[middle] > key){
                high = middle - 1;
            }else if(arr[middle] < key){
                low = middle + 1;
            }else{
                return middle;
            }
        }
        return -1;
    }

    //3.O(n^2) Searching
    void choose3(int type){
        reset();
        if(type == 1){
            System.out.println("Before O(n^2) Sorting:");
            for(int i=0;i<array_for_4th_sorting.length;i++){
                System.out.format(formatInfo,array_for_4th_sorting[i]);
                System.out.print(" ");
            }
            InsertSort(array_for_4th_sorting);
            System.out.println("");
            System.out.println("After O(n^2) Sorting:");

            for(int i=0;i<array_for_4th_sorting.length;i++){
                System.out.format(formatInfo, array_for_4th_sorting[i]);
            }
            System.out.println(" ");
        }
        else{
            System.out.format(column1Format,"InsertSort rst");
            start = System.nanoTime();
            InsertSort(array_for_4th_sorting);
            for(int i=0;i<array_for_4th_sorting.length;i++){
                System.out.format(formatInfo, array_for_4th_sorting[i]);
            }
            System.out.println(" ");
        }

    }

    private  void InsertSort(int[] a) {
        int i,j;
        for(i=1;i<a.length;i++){
            if(a[i]<a[i-1]){
                int temp=a[i];
                for(j=i-1;j>=0&&temp<=a[j];j--){
                    a[j+1]=a[j];
                    print(a);
                }
                a[j+1]=temp;
                print(a);
            }
        }
    }

    void choose5(){
        reset();
        choose3(2);
        List<Long> ad1 = initPerformance(3);

        reset();
        choose4(2);
        List<Long> ad2 = initPerformance(4);


        try{
            TestPoiAndJfreeChart1.exportChart(ad1,ad2,array_for_4th_sorting,"insert sort","quick sort");
        }
        catch(Exception e){

        }
    }

    static void reset(){
        timesList  = new ArrayList<>();
        arrList = new ArrayList<>();
        countList = new ArrayList<>();
        array_for_4th_sorting = new int[]{100,400,300,200,700,900,800,600,500};
    }

    //4.O(n*log(n)) Type of sorting
     void choose4(int type){
        reset();
        if(type == 1){
            System.out.println("Before O(n*log(n)) Sorting:");
            for(int i=0;i<array_for_4th_sorting.length;i++){
                System.out.format(formatInfo,array_for_4th_sorting[i]);
            }
            System.out.println("");
            System.out.println("After O(n*log(n)) Sorting:");
            start = System.nanoTime();
            quickSort(array_for_4th_sorting);
            for(int i=0;i<array_for_4th_sorting.length;i++){
                System.out.format(formatInfo,array_for_4th_sorting[i]);
            }
            System.out.println();
        }
        else{
            System.out.format(column1Format,"quick stort:");
            start = System.nanoTime();
            quickSort(array_for_4th_sorting);
            for(int i=0;i<array_for_4th_sorting.length;i++){
                System.out.format(formatInfo,array_for_4th_sorting[i]);
            }
            System.out.println();
        }
    }

     List<Long> initPerformance(int type){
         String sort ="";
         if(type==3){
             sort ="insert ";
         }else if(type == 4){
             sort ="quick ";
         }
        System.out.format(column1Format,sort+" stort ms:");
         long t_s = 0;
         List<Long> addTimes = new ArrayList();
        for(int i=0;i<array_for_4th_sorting.length;i++){
            t_s = t_s + timesList.get(i);
            addTimes.add(t_s);
            System.out.format(formatInfo,t_s);
        }
        System.out.println();
        for (int i=0;i<array_for_4th_sorting.length;i++){
            countList.add(count(array_for_4th_sorting[i],i));
        }

        System.out.format(column1Format,sort+" stort count:");
        countList.stream().forEach(o->System.out.format(formatInfo,o));
        System.out.println();
        return addTimes;

    }

     int count(int key,int index){
        int r = 0;
        for(int i=0;i<arrList.size();i++){
            String[] a = arrList.get(i).split(" ");
            if(a[index].trim().equals(key+"")){
                r = i+1;
                break;
            }
        }
        return r;
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    public static void quickSort(int[] arr, int start, int end) {

        if (start >= end) return;

        int middle = partition(arr, start, end);

        quickSort(arr, start, middle - 1);

        quickSort(arr, middle + 1, end);
    }

    public static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int left = start + 1;
        int right = end;
        while (left < right) {
            while (left < right && arr[left] <= pivot) left++;
            if (left != right) {
                exchange(arr, left, right);
                right--;
            }
        }

        if (left == right && arr[right] > pivot) right--;
        if (right != start) exchange(arr, start, right);
        return right;
    }
    private static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        print(arr);
    }

    private static void print(int[] arr){
        String rst = "";
        for(int i=0;i<arr.length;i++){
//            System.out.print(arr[i]);
//            System.out.print(" ");
            if(rst.equals("")){
                rst = rst + arr[i];
            }else{
                rst = rst +" "+ arr[i];
            }
        }
//        System.out.println(" ");
        arrList.add(rst);
        Long end = System.nanoTime();
        timesList.add(end-start);
        start = end;
    }
}



