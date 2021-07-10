package by.epamtc.sinitsyna.logic;

public class ServiceProvider {
	private AirCompanyLogic logic = new AirCompanyLogic();

	private ServiceProvider() {

	}

	private static class ProviderHelper {
		private static final ServiceProvider INSTANCE = new ServiceProvider();

	}

	public static ServiceProvider getInstance() {
		return ProviderHelper.INSTANCE;
	}

	public AirCompanyLogic getLogic() {
		return logic;
	}

	public void setLogic(AirCompanyLogic logic) {
		this.logic = logic;
	}

}
