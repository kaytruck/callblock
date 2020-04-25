package kaytruck.spring.callblock.exception;

public class BlockException extends RuntimeException {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public BlockException() {
        super();
	}

	public BlockException(String message) {
        super(message);
	}


}