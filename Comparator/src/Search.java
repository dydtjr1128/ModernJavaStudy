class Search<T> {
    public boolean BinarySearch(T[] arr, T target) {
        return true;
    }

    // 이 메소드를 이용하여 BSearch를 구현할 것! (제네릭의 비교연산)
    public <T extends Comparable<T>> int isGreater(T a, T b) {
        if (a.compareTo(b) > 0) {
            return 1;
        } else if (b.compareTo(a) > 0) {
            return -1;
        } else {
            return 0;
        }
    }

    // !!!구현해야하는 메소드!!!
    public <T extends Comparable<T>> boolean BSearch(T arr[], T target) {
        // 배열의 가장 작은값,큰값을 low,high에 부여
        T low = arr[0];
        T high = arr[arr.length];
        T mid;
        T tar = target;

        // high가 low보다 크지 않다면 바로 i=false가 되어 아래의 while문을 중단시킴
        boolean i = true;


        while (i) {
            // high가 low보다 크지 않다면 바로 i=false가 되어 while문을 중단시킴
            if (high.compareTo(low) > 0) {
                i = true;
            } else if (low.compareTo(high) > 0) {
                i = false;
            } else {
                i = false;

                // low,mid,high의 연산(제네릭이 아니라면 원래 이렇게...)
                mid = (low + high) / 2;

                if (arr[mid] == target)
                    return true;
                else if (arr[mid] > target)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            return false;

        }

    }
}