
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UnsortedCallDB implements callDB{

    //attribute
    //set it equal to different types of lists
    private List<Call> unsortedCallList;

    //constructor
    public UnsortedCallDB(Boolean use_arraylist) {
        //don't need "true" here -> standard way
        if(use_arraylist) {
            this.unsortedCallList = new ArrayList<>();
        } else {
            this.unsortedCallList = new LinkedList<>();
        }

    }

    public void index_call(Call x) {

        unsortedCallList.add(x);
    }

    public List<Call> search_calls(String source_number) {

        List<Call> sourceNumberCalls = new ArrayList<>();

        for(int i = 0; i < unsortedCallList.size(); i++) {
            Call call = unsortedCallList.get(i);
            if(call.getSourcePhoneNumber().equals(source_number)) {
                sourceNumberCalls.add(call);
            }
        }
        return sourceNumberCalls;
    }



}

