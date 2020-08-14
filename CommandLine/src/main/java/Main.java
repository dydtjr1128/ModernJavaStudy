import org.apache.commons.cli.*;

/**
 * @author : dydtjr1128
 * @project : Study
 * @github : https://github.com/dydtjr1128
 * @since : 2020-02-11
 */
public class Main {
    public static void main(String[] args) {
        boolean tt = Boolean.parseBoolean("true");
        System.out.println(tt);
        if (args.length != 0 && args[0].startsWith("-") == false) {
            args[0] = "-" + args[0];
        }

        CommandLineParser parser = new DefaultParser();
        Options options = makeCliOptions();
        try {
            CommandLine line = parser.parse(options, args);

            System.out.println("인자 목록");
            for(String arg : line.getArgList()) {
                System.out.println(arg);
            }
            System.out.println("===========" + line.hasOption("set-DB-info") + line.hasOption("3306") + line.hasOption("/asm/path"));
            String port = (line.getArgList().size() > 2) ? line.getArgList().get(2) : "3306";
            System.out.println("@@@@@@@@@@@@@@@@@@@@" + port);
            if (line.hasOption("setDB") || line.hasOption("set-DB-info")) {
                System.out.println(line.getArgList().get(0) + " " + port);
            } else if (line.hasOption("createDBUser") || line.hasOption("create-DB-user")) {
                System.out.println(line.getArgList().get(0) + " " + port);
            } else if (line.hasOption("smi") || line.hasOption("set-manager-ip")) {
                System.out.println(line.getArgList().get(0) + " " + port);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage() + "\n");
            new HelpFormatter().printHelp("asm4-application.jar", options);
        }

    }
    private static Options makeCliOptions() {
        Options options = new Options();
        options.addOption("version", false, "show now version");
        options.addOption("shutdown", false, "shutdown ASM4 application");
        options.addOption(Option.builder("chkSptExtDB")
                .longOpt("check-support-externalDB")
                .desc("check whether can support external DB in linux setup. It return 'yes' or 'no'.")
                .build());
        options.addOption(Option.builder("setDB")
                .longOpt("set-DB-info")
                .argName("ASM home path")
                .desc("set DB login info")
                .build());
        options.addOption(Option.builder("createDBUser")
                .longOpt("create-DB-user")
                .hasArg()
                .argName("ASM home path")
                .desc("set DB login info")
                .build());
//		options.addOption(Option.builder("li")
//				.longOpt("linux-init")
//				.hasArg()
//				.argName("ASM home path")
//				.desc("ASM4 linux init(ASM home, server key, DB)")
//				.build());
        options.addOption(Option.builder("smi")
                .longOpt("set-manager-ip")
                .desc("set manager IP bandwidth")
                .build());

        return options;
    }

}
