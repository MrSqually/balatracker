local logging = require("logging")
local state_log = logging.getLogger("state_log")
return {
    name = "dump",
    short_description = "dump game state to console",
    usage = "Usage: dump",
    on_call = function(console)
      state_log:info(G.P_CENTER_POOLS)
    end,
    on_complete = function(console, currentArg, previousArgs)
      return nil
    end
}
