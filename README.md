# CPASS BATCH
Batch component for CPass.

## Development
The entrypoint of the Batch module is the class `it.csi.cpass.cpassbatch.Main`, with redirects to the correct batch implementation via the parameters given.

For a local test of the execution, the jar MAY be invoked as
```bash
java -cp target\cpassbatch-1.0.0.jar it.csi.cpass.cpassbatch.Main <service> [...args]
```
The first parameter given to the invocation MUST be the service to be invoked, whereas the following, optional parameters, MAY be defined by the service.

## Configuration
In case a new profile is to be added, it MUST be referenced in the `<profiles>` section of the `pom.xml`, and the corresponding `<profile>.sql` file MUST be added in the `/profiles` folder.

### Properties configuration
- path.base: the base path for the service invocation (for local invocation it MAY be http://127.0.0.1:8080/rest/api/v1/)
