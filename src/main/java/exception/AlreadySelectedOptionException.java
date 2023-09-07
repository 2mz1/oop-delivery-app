package exception;

/**
 * Thrown when trying to select an option that already selected.
 */
public class AlreadySelectedOptionException extends RuntimeException {

    public AlreadySelectedOptionException() {
        super("이미 선택된 옵션입니다.");
    }

    public AlreadySelectedOptionException(String s) {
        super(s);
    }
}
