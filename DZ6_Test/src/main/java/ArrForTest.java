
public class ArrForTest {
    public int [] array(int arr[]){
        for (int i = arr.length-1; i >= 0  ; i--) {
            int a = arr[i];
            if(a == 4){
                int newArr[] = new int [(arr.length-1)-i];
                int amount = 0;
                for (int j = i+1; j <arr.length ; j++) {
                    newArr[amount++] = arr[j];
                }
                return newArr;
            }
        }
        throw new RuntimeException();
    }

    public boolean arrayContain(int [] arr){
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i] == 1){
                for (int j = 0; j <arr.length ; j++) {
                    if(arr[j] != 1){
                        return true;
                    }
                }
            }
            if(arr[i] == 4){
                for (int j = 0; j <arr.length ; j++) {
                    if(arr[j] !=4){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
