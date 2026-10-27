-- laser lua library "lalulib" functions

local lalulib = {_NAME="lalulib"}

function pairsSortedByKeys(tableObject)
   local sortedTable = {}

   for n in pairs(tableObject) do
      sortedTable[#sortedTable + 1] = n
   end
   table.sort(sortedTable)

   local iterVar = 0
   local iterFunction = function ()
      iterVar = iterVar + 1
      if sortedTable[iterVar] == nil then
         return nil
      else
         return sortedTable[iterVar], tableObject[sortedTable[iterVar]]
      end
   end
   return iterFunction
end

function dumpToString(object)

   function dumpInternal(object, dimension)
      if type(object) == "table" then
         local s = "{"
         dimension = dimension + 1
         local i = 0
         for key, value in pairsSortedByKeys(object) do
            i = i + 1
            if type(key) ~= "number" then
               key = '"' .. key .. '"'
            end
            if i > 1 then
               s = s .. ", "
            end
            s = s .. "[" .. key .. "] = "
            if (dimension < 10) then
               s = s .. dumpInternal(value, dimension)
            else
               s = s .. "<...>"
            end
         end
         dimension = dimension - 1
         return s .. "}"
      elseif type(object) == "string" then
         return  '"' .. object .. '"'
      elseif type(object) == "nil" then
         return  "nil"
      else
         return tostring(object)
      end
   end -- end of function dumpInternal

   local dimension = 0
   return dumpInternal(object, dimension)
end

function getTableValueCount(object)
   local count = 0
   if type(object) == "table" then
      for _ in pairs(object) do count = count + 1 end
   end
   return count
end

function isAvailable(...)
   local count = select('#', ...)
   for j = 1, count do
      local arg = select(j, ...)
      if (arg == nil) then
         return false
      end
   end
   return (count > 0)
end

function getNumber(s)
   local n = 0
   if (type(s) == "table") then
      s = table.concat(s)
   end
   if (type(s) == "number") then
      n = s
   elseif (s ~= nil) then
      n = tonumber(s)
      if (n == nil) then
         n = 0
      end
   end
   return n
end

function isNumber(...)
   local count = select('#', ...)
   for j = 1, count do
      local arg = select(j, ...)
      if (     (type(arg) ~= "number")
         and   ((type(arg) ~= "string") or (tonumber(arg) == nil))) then
         return false
      end
   end
   return (count > 0)
end

function isNotEmpty(...)
   local count = select('#', ...)
   for j = 1, count do
      local arg = select(j, ...)
      if (arg == nil) then
         return false
      end
      if ((type(arg) == "string") and ( arg == "")) then
         return false
      end
   end
   return (count > 0)
end

function isInRanges(num, ...)
   if (isNumber(num)) then
      local n = getNumber(num)
      local count = select('#', ...)
      if ((n == nil) or (count < 1)) then
         return false
      end
      for j = 1, count, 2 do
         if (j == count) then
            -- max is unbounded (infinite)
            local arg = select(j, ...)
            if (isNumber(arg)) then
               local min = getNumber(arg)
               if ((min ~= nil) and (n >= min)) then
                  return true
               end
            end
         else
            local argmin = select(j, ...)
            local argmax = select(j + 1, ...)
            if (isNumber(argmin) and (isNumber(argmax) or (argmax == nil))) then
               local min = getNumber(argmin)
               local max = nil
               if (argmax ~= nil) then
                  max = getNumber(argmax)
               end
               if ((min ~= nil) and (n >= min) and ((max == nil) or (n <= max))) then
                  return true
               end
            end
         end
      end
   end
   return false
end


function isInSet(value, ...)
   local count = select('#', ...)
   if ((value == nil) or (count < 1)) then
      return false
   end
   for j = 1, count do
      if (select(j, ...) == value) then
         return true
      end
   end
   return false
end

function getDigits(num, fromDigit, toDigit)
   if (isNumber(num, fromDigit) == false) then
      return ""
   end
   local fromD = getIntNumber(fromDigit)
   local toD = getIntNumber(toDigit or fromDigit)
   if ((fromD < 0) or (toD > fromD)) then
      return ""
   end
   local n = getAbsIntNumber(num)
   local s = "" .. n
   if (#s < fromD) then
      return ""
   end
   fromD = #s + 1 - fromD
   toD = #s + 1 - toD
   s = string.sub(s, fromD, toD)
   if (isNumber(s)) then
      return s
   else
      return ""
   end
end

function getIntNumber(s)
   local n = getNumber(s)
   if (type(n) == "number") then
      n = math.floor(n)
   else
      n = 0
   end
   return n
end

function getAbsIntNumber(s)
   return math.abs(getIntNumber(s))
end

function getDigitsVal(num, fromDigit, toDigit)
   return getIntNumber(getDigits(num, fromDigit, toDigit))
end

function spellNumber(numberValue) --spell number without space
   local s = ""
   if (isNumber(numberValue)) then
      local n = getIntNumber(numberValue)
      if (n > 0) then
         s = "" .. n
      end
   end
   return s
end

function spellNumberEx(numberValue) --add space before and after the number
   local s = ""
   if (isNumber(numberValue)) then
      local n = getIntNumber(numberValue)
      if (n > 0) then
         s = " " .. n .. " "
      end
   end
   return s
end
function getCommaWordOfDifferentLanguages(countryCode)
   if (countryCode == "DNK") then -- Danish
      return "komma"
   elseif (countryCode == "NLD") then -- Dutch
      return "komma"
   elseif (countryCode == "FRA") then -- French
      return "virgule"
   elseif (countryCode == "DEU") then -- German
      return "komma"
   elseif (countryCode == "ITA") then -- Italian
      return "virgola"
   elseif (countryCode == "NOR") then -- Norwegian
      return "komma"
   elseif (countryCode == "ESP" or countryCode == "MEX") then -- Spanish
      return "coma"
   elseif (countryCode == "SWE") then -- Swedish
      return "komma"
   else
      return ""
   end
end
-- unitArray: [1] meter s [2] meter p
--            [3] kilometer s [4] kilometer p [5] kilometer other
--            [6] foot s  [7] foot p
--            [8] yard s  [9] yard p
--            [10] mile s [11] mile p
--            [12] a quarter of a [13] half a [14] three quarters of a
function spellDistanceAndUnit(man, countryCode, unitArray, useNext)
   if (unitArray == nil or #unitArray == 0) then
      return ""
   end
   useNext = useNext or false
   local s = ""
   local distUnit = man.DistUnit
   local dist = man.Distance
   if (useNext) then
      distUnit = man.NextDistUnit
      dist = man.NextDistance
   end
   if (distUnit == "meter") then
      if (countryCode == "THA") then
         s = concEx(spellNumberEx(dist), unitArray[2])
      else
         s = conc(spellNumber(dist), unitArray[2])
      end
   elseif (distUnit == "kilometer") then
      local kilometerDistance = dist
      if (kilometerDistance < 10000) then
         local kilometerString = "";
         kilometerDistance = math.modf(dist / 1000);
         subkilometerValue = math.modf((dist - kilometerDistance * 1000) / 100);
         if (subkilometerValue == 0) then
            if (countryCode == "THA") then
               kilometerString = " " .. kilometerDistance .. " "
            else
               kilometerString = kilometerDistance;
            end
         else
            kilometerString = kilometerDistance
            if (man.project == "zeekr") then
               comma = getCommaWordOfDifferentLanguages(countryCode)
               if (comma == "") then
                  kilometerString = kilometerString .. "."
               else
                  kilometerString = kilometerString .. " " .. comma .. " "
               end
            else
               kilometerString = kilometerString .. "."
            end
            kilometerString = kilometerString .. subkilometerValue
            if (countryCode == "THA") then
               kilometerString = " " .. kilometerString .. " "
            end
         end
         if (countryCode == "THA") then
            s = concEx(kilometerString, unitArray[3])
         else
            if (kilometerDistance == 1 and subkilometerValue == 0) then
               s = conc(kilometerString, unitArray[3])
            else
               s = conc(kilometerString, unitArray[4])
            end
         end
      else
         kilometerDistance = math.modf(dist / 1000);
         if (countryCode == "THA") then
            s = concEx(spellNumberEx(kilometerDistance), unitArray[3])
         else
            if (countryCode == "ITA") then
               s = conc(spellNumber(kilometerDistance), unitArray[5])
            else
               s = conc(spellNumber(kilometerDistance), unitArray[4])
            end
         end
      end
   elseif (distUnit == "foot") then
      if (countryCode == "THA") then
         s = concEx(spellNumberEx(dist), unitArray[7])
      else
         s = conc(spellNumber(dist), unitArray[7])
      end
   elseif (distUnit == "yard") then
      if (countryCode == "THA") then
         s = concEx(spellNumberEx(dist), unitArray[9])
      else
         s = conc(spellNumber(dist), unitArray[9])
      end
   elseif (distUnit == "mile") then
      if (countryCode == "THA") then
         s = concEx(spellNumberEx(dist), unitArray[10])
      else
         if (dist == 1) then
            if (countryCode == "CHN_hant" and man.project ~= "zeekr") then
               s = s .. dist .. " " .. unitArray[11]
            else
               s = s .. dist .. " " .. unitArray[10]
            end
         else
            if (countryCode == "CHN_hant" and man.project == "zeekr") then
               s = s .. dist .. " " .. unitArray[10]
            else
               s = s .. dist .. " " .. unitArray[11]
            end
         end
      end
   elseif (distUnit == "quartermile") then
      if (dist == 1) then
         s = unitArray[12]
      elseif (dist == 2) then
         s = unitArray[13]
      elseif (dist == 3) then
         s = unitArray[14]
      end
      if (#s > 0) then
         if (countryCode == "THA") then
            s = concEx(s, unitArray[10])
         elseif (countryCode == "CHN_hant" and man.project ~= "zeekr") then
            s = conc(s, unitArray[11])
         else
            s = conc(s, unitArray[10])
         end
      end
   end
   return s
end

function trim(s)
   if (type(s) == "string") then
      return string.match(s, "^()%s*$") and "" or string.match(s, "^%s*(.*%S)")
   else
      return ""
   end
end

function convToString(arg)
   local s = ""
   if (type(arg) == "nil") then
      return s
   elseif (type(arg) == "string") then
      s = trim(arg)
   elseif ( (type(arg) == "number") or (type(arg) == "boolean")) then
      s = tostring(arg)
   elseif (type(arg) == "table") then
      s = table.concat(arg)
   end
   return s
end

function concat(val1, sep, val2) --concatenate the parameters val1 and val2 in not empty with the parameter sep
   if (val1 == nil) then
      val1 = ""
   else
      val1 = convToString(val1)
   end
   if (sep == nil) then
      sep = ""
   elseif (type(sep) ~= "string") then
      sep = convToString(sep)
   end
   if (val2 == nil) then
      val2 = ""
   else
      val2 = convToString(val2)
   end
   if (#val2 ~= 0) then
      if (#val1 == 0) then
         val1 = val2
      else
         val1 = val1 .. sep .. val2
      end
   end
   return val1
end

function conc( stringVal, ...) --concatenate the parameter with a space
   if (stringVal == nil) then
      stringVal = ""
   else
      stringVal = convToString(stringVal)
   end
   for j = 1, select('#', ...) do
      local arg = select(j, ...)
      stringVal = concat(stringVal, " ", arg)
   end
   return stringVal
end

function concEx( stringVal, ...) --concatenate the parameter without a space
   if (type(stringVal) == "nil") then
      return s
   elseif (type(stringVal) == "string" or (type(stringVal) == "number") or (type(arg) == "boolean")) then
      s = tostring(stringVal)
   elseif (type(stringVal) == "table") then
      s = table.concat(stringVal)
   end

   for j = 1, select('#', ...) do
      local arg = select(j, ...)
      stringVal = stringVal .. arg
   end
   return stringVal
end

function setPrototypeTable(originalTable, prototypeTable)
   if type(originalTable) == "table" and type(prototypeTable) == "table" then
      for key, value in pairs(originalTable) do
         if type(value) == "table" and prototypeTable[key] then
            setPrototypeTable(value, prototypeTable[key])
         end
      end
      local mt = {}
      mt.__index = prototypeTable
      setmetatable(originalTable, mt)
   end
   return originalTable
end

function copyObject(object)
   local result
   if type(object) == 'table' then
      result = {}
      for key, val in next, object, nil do
         result[copyObject(key)] = copyObject(val)
      end
   else -- number, string, boolean, etc
      result = object
   end
   return result
end

lalulib.dumpToString = dumpToString
lalulib.getTableValueCount = getTableValueCount
lalulib.isAvailable = isAvailable
lalulib.getNumber = getNumber
lalulib.isNumber = isNumber
lalulib.isNotEmpty = isNotEmpty
lalulib.isInRanges = isInRanges
lalulib.isInSet = isInSet
lalulib.getDigits = getDigits
lalulib.getIntNumber = getIntNumber
lalulib.getAbsIntNumber = getAbsIntNumber
lalulib.getDigitsVal = getDigitsVal
lalulib.trim = trim
lalulib.convToString = convToString
lalulib.concat = concat
lalulib.conc = conc
lalulib.concEx = concEx
lalulib.setPrototypeTable = setPrototypeTable
lalulib.copyObject = copyObject
lalulib.pairsSortedByKeys = pairsSortedByKeys

return lalulib
