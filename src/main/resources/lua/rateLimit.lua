local key = KEYS[1]
local limit = tonumber(ARGV[2])
local curr_permits = tonumber(redis.pcall("GET", key) or "0")
if curr_permits + 1 > limit then
    return 0
else
    redis.pcall("INCRBY", key, "1")
    redis.pcall("EXPIRE", key, ARGV[1])
    return 1
end
