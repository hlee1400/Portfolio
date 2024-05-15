
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SortedCallDB implements callDB {

    //auxiliary class
    private class PhoneCalls {

        //attributes
        private String source_number;

        private List<Call> call_list;

        //constructor
        public PhoneCalls(String phone_number, Boolean use_arraylist) {
            if(use_arraylist) {
                this.call_list = new ArrayList<>();
            } else {
                this.call_list = new LinkedList<>();
            }

            //phone number attribute is equal to what I was given
            //this.___ doesn't have to be the same on the other side
            this.source_number = phone_number;
        }

        public void add(Call x) {
            if(x.getSourcePhoneNumber().equals(source_number)) {
                call_list.add(x);
            } else {
                throw new IllegalArgumentException("This was not a call made from a specific phone number.");
            }
        }

        public List<Call>getCalls() {
            return call_list;
        }
        public String toString() {
            return String.format("Source number: %s; Size: %d ", source_number, call_list.size());
        }


    }


    //attributes PRIVATE

    private List<PhoneCalls> phoneCallList;
    private Boolean using_arraylists;

    //constructor

    public SortedCallDB(Boolean use_arraylist) {
        if(use_arraylist) {
            this.phoneCallList = new ArrayList<>();
        } else {
            this.phoneCallList = new LinkedList<>();
        }
        this.using_arraylists = use_arraylist;
    }

    public int uniqueSourceNumberCount() {
        return phoneCallList.size();

    }

    public void index_call(Call x) {


        if(phoneCallList.isEmpty()){
            PhoneCalls pc = new PhoneCalls(x.getSourcePhoneNumber(), using_arraylists);
            pc.add(x);
            phoneCallList.add(pc);
            return;
        }
        int low = 0;
        int high = phoneCallList.size() -1;
        while (low <= high){
            int mid = low + (high - low) /2;
            PhoneCalls midPC = phoneCallList.get(mid);
            int cmp = midPC.source_number.compareTo(x.getSourcePhoneNumber());
            if(cmp == 0){
                midPC.add(x);

                return;
            }else if(cmp < 0){
                low = mid + 1;
            }else{
                high = mid -1;
            }
        }
        PhoneCalls newPhoneCalls = new PhoneCalls(x.getSourcePhoneNumber(), using_arraylists);
        newPhoneCalls.add(x);
        phoneCallList.add(low, newPhoneCalls);

    }


    public void printPhoneCallList() {
        System.out.println(phoneCallList);
    }
    public List<Call> search_calls(String source_number) {
        List<Call> searchCallList = new ArrayList<>();

        int low = 0;
        int high = phoneCallList.size() -1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            PhoneCalls pcMid = phoneCallList.get(mid);
            int comparison = source_number.compareTo(pcMid.source_number);

            if(comparison > 0) {
                low = mid + 1;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                return pcMid.getCalls();
            }
        }
        return searchCallList;
    }


}

