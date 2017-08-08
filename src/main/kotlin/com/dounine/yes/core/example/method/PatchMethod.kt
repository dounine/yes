package com.dounine.yes.core.example.method

import com.dounine.yes.core.example.Example
import com.dounine.yes.core.request.RequestMethod

class PatchMethod(example: Example) : EntityEnclosingMethod(example,RequestMethod.PATCH) {
}